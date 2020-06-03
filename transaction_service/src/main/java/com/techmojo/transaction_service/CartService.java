package com.techmojo.transaction_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
	 	
		List<Product> result = (List<Product>) productRepository.findAll();
	         
	    if(result.size() > 0) {
	    	return result;
	            
	    } else {
	    	return new ArrayList<Product>();
	    }
	}
	
	public List<Cart> getAllProducts_c() {
	 	
		List<Cart> result = (List<Cart>) cartRepository.findAll();
	         
	    if(result.size() > 0) {
	    	return result;
	            
	    } else {
	    	return new ArrayList<Cart>();
	    }
	}
	 public void saveCart(Cart entity)  {
		 if(entity.getId()!= null) {
			 cartRepository.save(entity);   
		 }
	 }
}
