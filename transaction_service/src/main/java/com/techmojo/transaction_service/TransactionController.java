package com.techmojo.transaction_service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TransactionController {
	
	@Autowired
    AccountService a_service;
	
	@Autowired
    CartService c_service;
	
	@Autowired
    TransactionService t_service;
	

	
	
    @GetMapping("/home")
    public String home(Model model) {
    	String redirectUrl = "http://localhost:8080/home";
      	 return "redirect:" + redirectUrl;
    }
	
	 @RequestMapping(path = {"/addamount/{id}"})
	    public String addAmountById(Model model, @PathVariable("id") Optional<Long> id) 
	                            throws RecordNotFoundException 
	    {
		 	UserAccounts acc = a_service.getAccountById(id.get());
	        model.addAttribute("userAccounts", acc);
	        Transactions t = new Transactions();
			t.setMobileid(id.get().toString());
			Timestamp date_time = new Timestamp(System.currentTimeMillis());
			t.setDate_time(date_time);
			t.setMessage("Account is edited by the Admin");
			t_service.saveTrans(t);
	    	return "add-edit-account";
	    }
	 
	    @RequestMapping(path = "/createAccount", method = RequestMethod.POST)
	    public String createOrUpdateAccount(UserAccounts account) 
	    {
	        a_service.saveAccount(account);
	        String redirectUrl = "http://localhost:8080/admin";
	        return "redirect:" + redirectUrl;
	    }
	 
	    @RequestMapping(path = {"/transactions/{id}"})
	    public String Transcations(Model model, @PathVariable("id") Optional<Long> id) 
	                            throws RecordNotFoundException 
	    {
	    	List<Transactions> transactions = (List<Transactions>) t_service.getAllTrans();
	    	List<Transactions> id_trans = new ArrayList<Transactions>();
	    	for (Transactions var : transactions) {
	    		if(var.getMobileid().equals(id.get().toString())) {
	    			id_trans.add(var);
	    		}
	    	}
	    	model.addAttribute("transaction",id_trans);

	    	return "transaction-page";
	    }

	    @RequestMapping(path = {"/addwallet/{id}"})
	    public String addWalletById(Model model, @PathVariable("id") Optional<Long> id, Integer wallet) 
                    throws RecordNotFoundException 
{
	    	List<Product> products = (List<Product>) c_service.getAllProducts();
	    	UserAccounts acc = a_service.getAccountById(id.get());
	    	UserAccounts account = acc;
	    	
	    	if(account.getAccount()+account.getWallet() > wallet) {
	    		Integer temp = wallet - account.getWallet();
	    		account.setAccount(account.getAccount()-temp);
	    		account.setWallet(wallet);
	    		a_service.saveAccount(account);
	    		Transactions t = new Transactions();
	    		t.setMobileid(id.get().toString());
	    		Timestamp date_time = new Timestamp(System.currentTimeMillis());
	    		t.setDate_time(date_time);
	    		t.setMessage("Amount of rupees "+ temp.toString()+ " is added from account to the wallet");
	    		t_service.saveTrans(t);
	    		Warning w_war = new Warning();
				w_war.setFlag(false);
				w_war.setMessage("");
				model.addAttribute("w_war", w_war);
	    	} else {
	    		Warning w_war = new Warning();
				w_war.setFlag(true);
				w_war.setMessage("Invalid Amount in your account to add to the wallet");
				model.addAttribute("w_war", w_war);
				
	    	}
	    	Warning p_war = new Warning();
			p_war.setFlag(false);
			p_war.setMessage("");
			model.addAttribute("p_war", p_war);
	        model.addAttribute("userAccounts", acc);
	        model.addAttribute("products", products);
	            return "user-shopping";
	        
	    }
	    
	    @RequestMapping(path = {"/buyproduct/{id}/price/{price}/name/{name}"})
	    public String buyproductById(Model model, @PathVariable("id") Optional<Long> id, @PathVariable("price") Optional<Integer> price, @PathVariable("name") Optional<String> name) 
	    	        throws RecordNotFoundException 
	    	{
	    	List<Product> products = (List<Product>) c_service.getAllProducts();
	    	UserAccounts acc = a_service.getAccountById(id.get());
	    	UserAccounts account = acc;
	    	if(price.get() <= account.getWallet()) {
	    		account.setWallet(account.getWallet() - price.get());
	    		a_service.saveAccount(account);
	    		Transactions t = new Transactions();
	    		t.setMobileid(id.get().toString());
	    		Timestamp date_time = new Timestamp(System.currentTimeMillis());
	    		t.setDate_time(date_time);
	    		t.setMessage("An "+ name.get()+" of cost "+ price.get().toString()+ " is bought by the user");
	    		Cart car = new Cart();
	    		car.setMobileid(id.get().toString());
	    		car.setProductname(name.get());
	    		car.setPrice(price.get());
	    		c_service.saveCart(car);
	    		t_service.saveTrans(t);
	    		Warning p_war = new Warning();
	    		p_war.setFlag(false);
	    		p_war.setMessage("");
	    		model.addAttribute("p_war", p_war);
	    	} else {
	    		Warning p_war = new Warning();
	    		p_war.setFlag(true);
	    		p_war.setMessage("Invalid amount in your wallet to buy this product");
	    		model.addAttribute("p_war", p_war);
	    	}
	    	Warning w_war = new Warning();
			w_war.setFlag(false);
			w_war.setMessage("");
			model.addAttribute("w_war", w_war);
	    	model.addAttribute("userAccounts", acc);
	    	model.addAttribute("products", products);
	            return "user-shopping";
	        
	    }
	 
	    @RequestMapping(path = "/viewAccount")
	    public String getAccountById(String mobileid,Model model,@RequestParam(value = "wallet", required = false) Integer wallet) 
	                            throws RecordNotFoundException 
	    {
	    	if(mobileid.equals("")) {
		    	Warning u_war = new Warning();
				u_war.setFlag(true);
				u_war.setMessage("Invalid Mobile Id");
		    	model.addAttribute("u_war", u_war);
	    		return "user";
	    	}else {
	    		Optional<UserAccounts> acc = a_service.getAccountByIdOpt(Long.parseLong(mobileid));
	    		
	    		List<Product> products = (List<Product>) c_service.getAllProducts();
	    		if(acc.isPresent()) {
	    			Warning w_war = new Warning();
	    			w_war.setFlag(false);
	    			w_war.setMessage("");
	    			Warning p_war = new Warning();
	    			p_war.setFlag(false);
	    			p_war.setMessage("");
	    			model.addAttribute("w_war", w_war);
	    			model.addAttribute("p_war", p_war);
	    			model.addAttribute("userAccounts", acc.get());
	    			model.addAttribute("products", products);
	    			return "user-shopping";
	    		} else {
	    	    	Warning u_war = new Warning();
	    			u_war.setFlag(true);
	    			u_war.setMessage("Mobile number entered is Invalid / Not registered");
	    	    	model.addAttribute("u_war", u_war);
	    			return "user";
	    		}
	    	}
	    }
	    @RequestMapping(path = {"/viewcart/{id}"})
	    public String viewcart(Model model, @PathVariable("id") Optional<Long> id) 
	                            throws RecordNotFoundException 
	    {
	    	List<Cart> cart = (List<Cart>) c_service.getAllProducts_c();
	    	List<Cart> id_cart = new ArrayList<Cart>();
	    	for (Cart var : cart) {
	    		if(var.getMobileid().equals(id.get().toString())) {
	    			id_cart.add(var);
	    		}
	    	}
	    	model.addAttribute("cart",id_cart);

	    	return "cart-page";
	    }
	    @RequestMapping("/user")
	    public String user(Model model,@RequestParam(value = "mobileid", required = false) String mobileid) {

	    	Warning u_war = new Warning();
			u_war.setFlag(false);
			u_war.setMessage("");
	    	model.addAttribute("u_war", u_war);
	        return "user";
	    }
}
