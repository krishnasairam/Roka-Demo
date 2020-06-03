package com.techmojo.transaction_service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
	@Autowired
	private AccountsRepository accountsRepository;
	public UserAccounts getAccountById(Long id) throws RecordNotFoundException {
		 
		Optional<UserAccounts> account = accountsRepository.findById(id);
		if(account.isPresent()) {
			return account.get();
	        } else {
	        	UserAccounts n_account = new UserAccounts();
	            n_account.setMobile(id);
	            n_account.setAccount(0);
	            n_account.setWallet(0);
	            return n_account;
	    }
	}
	public Optional<UserAccounts> getAccountByIdOpt(Long id) throws RecordNotFoundException {
		 
		Optional<UserAccounts> account = accountsRepository.findById(id);

			return account;
	}
	 
	 public UserAccounts saveAccount(UserAccounts entity) {
	            entity = accountsRepository.save(entity);            
	            return entity;
	    }

}
