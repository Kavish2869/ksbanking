package com.cg.banking.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;
import com.cg.banking.services.BankingServicesImpl;
@Controller
public class BankingServiceController {
	
	@Autowired
	BankingServicesImpl bankingServices;
	
	@RequestMapping("/registerAccount")
	public ModelAndView registerAssociate(@ModelAttribute Account account) throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		account = bankingServices.openAccount(account);
		return new ModelAndView("registrationSuccessPage","account",account);
	
	}
	@RequestMapping("/deposite")
	public ModelAndView depositeAmount(@RequestParam("account.accountNo") int accountNo,@RequestParam float amount) throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException, AccountNotFoundException, AccountBlockedException {		
		float accountBalance = bankingServices.depositAmount(accountNo, amount);
		return new ModelAndView("depositeSuccess","accountBalance",accountBalance);
	
	}
	@RequestMapping("/withdraw")
	public ModelAndView withdrawAmount(@RequestParam("account.accountNo") int accountNo,@RequestParam float amount,@RequestParam("account.pinNumber") int pinNumber) throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException, AccountNotFoundException, AccountBlockedException, InsufficientAmountException, InvalidPinNumberException {		
		float accountBalance = bankingServices.withdrawAmount(accountNo, amount, pinNumber);
return new ModelAndView("depositeSuccess","accountBalance",accountBalance);
	
	}
	@RequestMapping("/fundT")
	public ModelAndView fundTransfer(@RequestParam("account.accountNo") int senderAccountNo,@RequestParam("account.accountNo") int recieverAccountNo,@RequestParam float amount,@RequestParam("account.pinNumber") int senderPinNumber) throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException, AccountNotFoundException, AccountBlockedException, InsufficientAmountException, InvalidPinNumberException {		
		boolean status = bankingServices.fundTransfer(senderAccountNo, recieverAccountNo, amount, senderPinNumber);
return new ModelAndView("fundTransferSuccess","status",status);
	
	}
	@RequestMapping("/showTransaction")
	public ModelAndView transactions(@RequestParam("account.accountNo") int accountNo ) throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException, AccountNotFoundException, AccountBlockedException, InsufficientAmountException, InvalidPinNumberException {		
		List<Transaction> trans = bankingServices.getAccountAllTransaction(accountNo);
return new ModelAndView("transactionsSuccess","trans",trans);
	
	}
	@RequestMapping("/showDetails")
	public ModelAndView getAccountDetails(@RequestParam("account.accountNo") int accountNo ) throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException, AccountNotFoundException, AccountBlockedException, InsufficientAmountException, InvalidPinNumberException {		
		Account account = bankingServices.getAccountDetails(accountNo);
		return new ModelAndView("getDetailsSuccess","account",account);
	
	}
//	@RequestMapping("/associateDetails")
//	public ModelAndView getAssociateDetails(@RequestParam int associateId) throws AssociateDetailNotFoundException {
//		Associate associate = bankingServices.getAssociateDetails(associateId);
//		return new ModelAndView("findAssociateDetailsPage","associate",associate);
//	}
}
