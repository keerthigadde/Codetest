package com.anz.cam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anz.cam.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>,PagingAndSortingRepository<Transaction,Long>{

    @Query("SELECT t FROM Transaction t WHERE t.transactionDate LIKE :date")
    List<Transaction> findTransactionsByDate(@Param("date") Date date);

    @Query("SELECT t FROM Transaction t WHERE t.transactionDate >= :dateFrom AND t.transactionDate <= :dateTo")
    List<Transaction> findTransactionsByPeriod(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Transaction u WHERE u.account.accountNumber = :number")
    boolean existsByNumber(@Param("number") String number);

    @Query("select t from Transaction t where t.account.accountNumber = :number")
    Page<Transaction> findTransactionsByNumber(@Param("number") String number,Pageable paging);

}
