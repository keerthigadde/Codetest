package com.anz.cam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anz.cam.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, PagingAndSortingRepository<Account, Long> {
	@Query("from Account where customerId =:custId")
	List<Account> findAccountsByCustomerId(@Param("custId") String custId);

	@Query("from  Account where accountNumber = :accNum")
	Optional<Account> findAccountByNumber(@Param("accNum") String accNum);

	@Query("select case WHEN COUNT(u) > 0 THEN true ELSE false END FROM Account u WHERE u.accountNumber = :accNum")
	boolean existsByAccountNumber(@Param("accNum") String accNum);
}
