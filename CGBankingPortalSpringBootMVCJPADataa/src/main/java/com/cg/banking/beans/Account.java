package com.cg.banking.beans;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
@Entity
public class Account {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int accountNo;
private int pinNumber;
@NotEmpty
private String accountType;
private String accountStatus;
private float accountBalance;

@MapKey
@OneToMany(mappedBy="account",fetch=FetchType.EAGER)
public Map<Integer,Transaction> transactions=new HashMap<Integer, Transaction>();

public Account(String accountType, float initBalance) {
	this.accountType=accountType;
	this.accountBalance=initBalance;
}



public Account(int accountNo, int pinNumber, String accountType, String accountStatus, float accountBalance,
		Map<Integer, Transaction> transactions) {
	super();
	this.accountNo = accountNo;
	this.pinNumber = pinNumber;
	this.accountType = accountType;
	this.accountStatus = accountStatus;
	this.accountBalance = accountBalance;
	this.transactions = transactions;
}

public Account() {}

public Account(Account account) {}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Float.floatToIntBits(accountBalance);
	result = prime * result + (int) (accountNo ^ (accountNo >>> 32));
	result = prime * result + ((accountStatus == null) ? 0 : accountStatus.hashCode());
	result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
	result = prime * result + pinNumber;
	result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Account other = (Account) obj;
	if (Float.floatToIntBits(accountBalance) != Float.floatToIntBits(other.accountBalance))
		return false;
	if (accountNo != other.accountNo)
		return false;
	if (accountStatus == null) {
		if (other.accountStatus != null)
			return false;
	} else if (!accountStatus.equals(other.accountStatus))
		return false;
	if (accountType == null) {
		if (other.accountType != null)
			return false;
	} else if (!accountType.equals(other.accountType))
		return false;
	if (pinNumber != other.pinNumber)
		return false;
	if (transactions == null) {
		if (other.transactions != null)
			return false;
	} else if (!transactions.equals(other.transactions))
		return false;
	return true;
}

public int getaccountNo() {
	return accountNo;
}

public void setaccountNo(int accountNo) {
	this.accountNo = accountNo;
}

public int getPinNumber() {
	return pinNumber;
}

public void setPinNumber(int pinNumber) {
	this.pinNumber = pinNumber;
}

public String getaccountType() {
	return accountType;
}

public void setaccountType(String accountType) {
	this.accountType = accountType;
}

public String getAccountStatus() {
	return accountStatus;
}

public void setAccountStatus(String accountStatus) {
	this.accountStatus = accountStatus;
}

public float getaccountBalance() {
	return accountBalance;
}

public void setaccountBalance(float accountBalance) {
	this.accountBalance = accountBalance;
}

public Map<Integer, Transaction> getTransactions() {
	return transactions;
}

public void setTransactions(Map<Integer, Transaction> transactions) {
	this.transactions = transactions;
}



@Override
public String toString() {
	return "Account [accountNo=" + accountNo + ", pinNumber=" + pinNumber + ", accountType=" + accountType
			+ ", accountStatus=" + accountStatus + ", accountBalance=" + accountBalance + ", transactions="
			+ transactions + "]";
}

}