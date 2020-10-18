package com.anz.cam.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.anz.cam.model.Account;
import com.anz.cam.payload.AccountDto;
import com.anz.cam.service.AccountService;
import com.anz.cam.utils.Constants;
import com.anz.cam.utils.NumberGeneratorUtil;

/**
 * REST controller for the accounts
 * 
 * @author keerthi gadde created on 10-18-2019
 * 
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	protected AccountService accountService;

	@PostMapping
	public ResponseEntity<String> createAccount(@Valid @RequestBody AccountDto accountDto) {
		String accountNumber = accountService.createAccount(convertDtoToAccount(accountDto));
		return new ResponseEntity<String>("Generate Account Number " + accountNumber, HttpStatus.CREATED);
	}

	@GetMapping
	public List<AccountDto> accounts(@RequestParam(required = false, defaultValue = Constants.PAGE_NO) Integer pageNo,
			@RequestParam(required = false, defaultValue = Constants.ITEMS_PER_PAGE) Integer pageSize,
			@RequestParam(required = false, defaultValue = "accountNumber") String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		List<AccountDto> result = accountService.findAll(paging).stream().map(account -> convertAccountToDto(account))
				.collect(Collectors.toList());
		return result;
	}

	private Account convertDtoToAccount(AccountDto accountDto) {
		Account account = modelMapper.map(accountDto, Account.class);
		account.setAccountNumber(NumberGeneratorUtil.accountNumberGenerator());
		account.setAccName(accountDto.getCurrency().name() + accountDto.getAccType() + (int) (Math.random() * 10));
		return account;
	}

	private AccountDto convertAccountToDto(Account account) {
		AccountDto accountDto = modelMapper.map(account, AccountDto.class);
		accountDto.setAccntUIType(account.getAccType().getAccountType());
		return accountDto;
	}
}
