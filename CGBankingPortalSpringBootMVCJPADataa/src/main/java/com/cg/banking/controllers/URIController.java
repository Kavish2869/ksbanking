package com.cg.banking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
@Controller
public class URIController {
	private Account account;
	private Transaction transaction;
	@RequestMapping(value= {"/","index"})
	public String getIndexPage() {
		return "indexPage";
	}

	@RequestMapping("/registration")
	public String getRegistrationPage() {
		return "registrationPage";
	}

	@RequestMapping("/depositeAmount")
	public String getDepositeAmountPage() {
		return "depositeAmount";
	}
	@RequestMapping("/withdrawAmount")
	public String getWithdrawAmountPage() {
		return "withdrawAmount";
	}
	@RequestMapping("/fundTransfer")
	public String getFundTransferPage() {
		return "fundTransfer";
	}
	@RequestMapping("/accountDetails")
	public String getAccountDetailsPage() {
		return "getAccountDetails";
	}
	@RequestMapping("/transaction")
	public String getTransactionPage() {
		return "transactions";
	}
	@ModelAttribute
	public Account getAccount() {
		account = new Account();
		return account;
	}
	@ModelAttribute
	public Transaction getTransaction() {
		transaction = new Transaction();
		return transaction;
	}
}
