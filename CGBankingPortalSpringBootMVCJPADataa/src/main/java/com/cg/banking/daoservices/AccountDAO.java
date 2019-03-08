package com.cg.banking.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.banking.beans.Account;

public interface AccountDAO extends JpaRepository<Account, Integer>{
//Account save(Account account);
//boolean update(Account account);
//Account findOne(long accountNo);
//List<Account> findAll();
}