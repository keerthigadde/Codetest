package com.anz.cam.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anz.cam.model.Transaction;
import com.anz.cam.payload.TransactionDto;
import com.anz.cam.service.AccountService;
import com.anz.cam.service.TransactionService;
import com.anz.cam.utils.Constants;

/**
 * REST controller for the transactions
 * 
 * @author keerthi gadde created on 10-18-2019
 * 
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	protected TransactionService transactionService;
	@Autowired
	protected AccountService accountService;

	@PostMapping
	public ResponseEntity<Void> createTransaction(@RequestBody TransactionDto transactionDto) {
		transactionService.createTransaction(convertDtoToTransaction(transactionDto));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public Page<TransactionDto> transactions(@RequestParam String accountNumber,
			@RequestParam(required = false, defaultValue = Constants.PAGE_NO) Integer pageNo,
			@RequestParam(required = false, defaultValue = Constants.ITEMS_PER_PAGE) Integer pageSize,
			@RequestParam(required = false, defaultValue = "transactionId") String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return transactionService.findAllTransactionsByAccountNumber(accountNumber, paging)
				.map(transaction -> convertTransactionToDto(transaction));
	}

	private Transaction convertDtoToTransaction(TransactionDto transactionDto) {
		Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
		transaction.setAccount(accountService.findByNumber(transactionDto.getAccountNumber()));
		return transaction;
	}

	private TransactionDto convertTransactionToDto(Transaction transaction) {
		TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
		transactionDto.setAccountName(transaction.getAccount().getAccName());
		transactionDto.setAccountNumber(transaction.getAccount().getAccountNumber());
		return transactionDto;
	}

}
