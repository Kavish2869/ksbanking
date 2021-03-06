package com.cg.banking.daoservices;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.banking.beans.Transaction;

public interface TransactionDAO extends JpaRepository<Transaction,Integer> {
@Query("from Transaction t where t.account.accountNo=:accountNo")
 public List<Transaction> showTransaction(@Param("accountNo") int accountNo);
}
