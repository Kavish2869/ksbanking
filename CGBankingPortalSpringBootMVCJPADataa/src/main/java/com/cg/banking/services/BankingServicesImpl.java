package com.cg.banking.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.TransactionDAO;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;
@Component("bankingServices")
public class BankingServicesImpl implements  BankingServices{
	int PIN_NUMBER_COUNTER=500;
	@Autowired
	AccountDAO service;
	@Autowired
	TransactionDAO transactionService;
	Account account;
	@Override
	public Account openAccount(Account account)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		if(!(account.getaccountType().equalsIgnoreCase("Savings") | account.getaccountType().equalsIgnoreCase("Current")))
			throw new InvalidAccountTypeException("Invalid Account Type");
		if(account.getaccountBalance()<0 | account.getaccountBalance()<500 )
			throw new InvalidAmountException("Invalid Amount!!! Enter Amount > 500");
		    account = new Account(account.getaccountType(),account.getaccountBalance());
			account.setAccountStatus("Active");
			account.setPinNumber(++PIN_NUMBER_COUNTER);
		account = service.save(account);
		return account; 
	}
	@Override
	public List<Account> getAllAccountDetails() throws BankingServicesDownException {
		return service.findAll();
	}
	@Override
	public Account getAccountDetails(int accountNo) throws AccountNotFoundException, BankingServicesDownException {
		account= service.findById(accountNo).orElseThrow(()->new AccountNotFoundException("Account Not Found"));	
		return account;
				
	}
	
	@Override
	public float depositAmount(int accountNo, float amount)
		throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
		account=service.findById((int)accountNo).orElseThrow(()->new AccountNotFoundException("Account Not Found"));
		float finalAmount=(account.getaccountBalance())+amount;
		account.setaccountBalance(finalAmount);
		service.save(account);
		
		Transaction transaction=new Transaction();
		transaction.setAmount(amount);
		transaction.setaccount(account);
		transaction.setTransactionType("Credit");
		transactionService.save(transaction);
		return account.getaccountBalance();
	}

	@Override
	public float withdrawAmount(int accountNo, float amount, int pinNumber) throws InsufficientAmountException,
			AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
		account=service.findById((int)accountNo).orElseThrow(()->new AccountNotFoundException("Account Not Found"));
		if(pinNumber==account.getPinNumber()) {
			if(amount<account.getaccountBalance()) {
			float newAmount=account.getaccountBalance()-amount;
			account.setaccountBalance(newAmount);
			service.save(account);
			
			Transaction transactionWith=new Transaction();
			transactionWith.setTransactionType("Money Withdrawn");
			transactionWith.setAmount(amount);
			transactionWith.setaccount(account);
			transactionService.save(transactionWith);
			return newAmount;
			}
			else
				throw new InsufficientAmountException("Insufficient Amount");
		}
		else
			throw new InvalidPinNumberException("Invalid PIN Number!!!");
	}
	
	@Override
	public boolean fundTransfer(int accountNoFrom, int accountNoTo, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException {
		
		Transaction transactionFT=new Transaction();
		transactionFT.setAmount(transferAmount);
		
		account=service.findById(accountNoFrom).orElseThrow(()->new AccountNotFoundException("Account Not Found"));
		if(pinNumber==account.getPinNumber()) {
			if(transferAmount<account.getaccountBalance()) {
			float deductedAmount=account.getaccountBalance()-transferAmount;
			account.setaccountBalance(deductedAmount);
			service.save(account);
			transactionFT.setTransactionType("Money Transfered");
			//transactionFT.setAmount(transferAmount);
			transactionFT.setaccount(account);
			transactionService.save(transactionFT);
			//transactionService.update(transactionFT);
			}
			else
				throw new InsufficientAmountException("Insufficient Amount!!!");
		}
		else
			throw new InvalidPinNumberException("Invalid Pin Number!!!");
		account=service.findById((int)accountNoTo).orElseThrow(()->new AccountNotFoundException("Account Not Found"));
			float addedAmount=account.getaccountBalance()+transferAmount;
				account.setaccountBalance(addedAmount);
			    service.save(account);
				transactionFT.setTransactionType("Money Deposited");
				transactionFT.setAmount(transferAmount);
				transactionFT.setaccount(account);

				transactionService.save(transactionFT);
				//transactionService.update(transactionFT);
				return true;
	}
	@Override
	public List< Transaction> getAccountAllTransaction(int accountNo)
			throws BankingServicesDownException, AccountNotFoundException {
		account=service.findById((int)accountNo).orElseThrow(()->new AccountNotFoundException("Account Not Found"));
		//Account account=service.findOne(accountNo);
		//Map<Transaction> List=new LinkedList<Transaction>(account.transactions);
List<Transaction> list=transactionService.findAll();		
		if(list==null)
			throw new BankingServicesDownException("No Transactions to be Displayed!!!");
			return list;
		}

	@Override
	public String accountStatus(int accountNo)
			throws BankingServicesDownException, AccountNotFoundException, AccountBlockedException {
		return null;
	}

}

