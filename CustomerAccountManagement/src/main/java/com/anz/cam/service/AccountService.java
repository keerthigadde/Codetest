package com.anz.cam.service;
/**
 * service method for Account
 * @author Keerthi Gadde
 *
 */
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anz.cam.exception.ResourceNotFoundException;
import com.anz.cam.model.Account;
import com.anz.cam.model.Transaction;
import com.anz.cam.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepos;

	@Transactional
	public List<Account> findAll(Pageable paging) {
		Page<Account> pagedResult = accountRepos.findAll(paging);
		return pagedResult.hasContent() ? pagedResult.getContent() : new ArrayList<Account>();

	}

	@Transactional
	public List<Account> findAll() {
		return accountRepos.findAll();
	}

	public Account findAccountById(Long id) {
		return accountRepos.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with accountId " + id));
	}

	@Transactional
	public String createAccount(Account accountRequest) {
		Account account = accountRepos.save(accountRequest);
		return account.getAccountNumber();
	}

	@Transactional
	public void deleteAccount(Long[] ids) {
		for (Long id : ids)
			accountRepos.deleteById(id);
	}

	public List<Account> findCustomerAccounts(String customerId) {
		List<Account> accounts = accountRepos.findAccountsByCustomerId(customerId);
		return accounts;
	}

	public List<Transaction> retrieveAllTransactions(String accountNumber) {
		Account account = findByNumber(accountNumber);
		return account.getTransactions();
	}

	public Account findByNumber(String number) {
		return accountRepos.findAccountByNumber(number)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with accountNumber " + number));
	}

	public boolean existByNumber(String number) {
		return accountRepos.existsByAccountNumber(number);
	}

}
