package com.anz.cam.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anz.cam.exception.CAMException;
import com.anz.cam.model.Account;
import com.anz.cam.model.Transaction;
import com.anz.cam.model.modelutils.TransactionType;
import com.anz.cam.repository.AccountRepository;
import com.anz.cam.repository.TransactionRepository;
/**
 * service method for Transaction
 * @author Keerthi Gadde
 *
 */
@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Transactional
	public void createTransaction(Transaction transaction) {
		Account account = transaction.getAccount();
		if (transaction.getTransType().equals(TransactionType.Debit)) {
			BigDecimal currentBalance = account.getBalance();
			BigDecimal newBalance = currentBalance.subtract(transaction.getAmount());
			if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
				account.setBalance(newBalance);
				accountRepository.save(account);
			} else {
				throw new CAMException("Not enough money in account for transaction");
			}

		} else if (transaction.getTransType().equals(TransactionType.Credit)) {
			BigDecimal currentBalance = account.getBalance();
			if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal newBalance = currentBalance.add(transaction.getAmount());
				account.setBalance(newBalance);
				accountRepository.save(account);

			} else {
				throw new CAMException("No suufficient transaction ammount");
			}

		}
		transactionRepository.save(transaction);
	}

	@Transactional
	public Page<Transaction> findAllTransactionsByAccountNumber(String accountNumber, Pageable pageable) {
		return transactionRepository.findTransactionsByNumber(accountNumber, pageable);
	}

}
