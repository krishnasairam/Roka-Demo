package com.techmojo.transaction_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Transactions> getAllTrans() {
	 	
		List<Transactions> result = (List<Transactions>) transactionRepository.findAll();
	         
	    if(result.size() > 0) {
	    	return result;
	            
	    } else {
	    	return new ArrayList<Transactions>();
	    }
	}
	 public void saveTrans(Transactions entity)  {
		 if(entity.getId()!= null) {
			 transactionRepository.save(entity);   
		 }
	 }
}
