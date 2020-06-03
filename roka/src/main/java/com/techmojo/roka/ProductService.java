package com.techmojo.roka;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
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
	public Product getproductById(Integer id) throws RecordNotFoundException {
		 
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
	        } else {
	            throw new RecordNotFoundException("No employee record exist for given id");
	    }
	}
	 
	 public void saveProduct(Product entity)  {
		 if(entity.getId()!= null) {
			 productRepository.save(entity);   
		 }
	 }
	 
	  public void deleteUserById(Integer id) throws RecordNotFoundException 
	    {
	        Optional<Product> user = productRepository.findById(id);
	         
	        if(user.isPresent()) 
	        {
	        	productRepository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No employee record exist for given id");
	        }
	    }
}
