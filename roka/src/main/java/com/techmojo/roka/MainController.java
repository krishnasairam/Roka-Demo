package com.techmojo.roka;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // This means that this class is a Controller
public class MainController implements ErrorController{
	@Autowired
    UserService service;
	
	@Autowired
    ProductService p_service;
	
	 @GetMapping("/")
	    public String home1() {
	        return "/login";
	    }

	 @GetMapping("/error")
	    public String error() {
	        return "/home";
	 }
	 
	 @Override()
	 public String getErrorPath() {
		 return "/error";
	 }
	    @GetMapping("/home")
	    public String home(Model model) {
	        return "/home";
	    }
	    
	    @GetMapping("/login")
	    public String login() {
	        return "/login";
	    }
	    
    @GetMapping(path="/admin")
    public String getAllEmployees(Model model) 
    {
        List<User> list = service.getAllUsers();
        List<Product> products = p_service.getAllProducts();
        model.addAttribute("users", list);
        model.addAttribute("products", products);
        return "/list-employees";
    }
 
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) 
                            throws RecordNotFoundException 
    {
        if (id.isPresent()) {
            User entity = service.getUserById(id.get());
            model.addAttribute("user", entity);
        } else {
            model.addAttribute("user", new User());
        }
        return "add-edit-employee";
    }
    
    @RequestMapping(path = {"/addproduct", "/addproduct/{id}"})
    public String addProduct(Model model, @PathVariable("id") Optional<Integer> id) throws RecordNotFoundException 
    {
    	if (id.isPresent()) {
            Product entity = p_service.getproductById(id.get());
            model.addAttribute("product", entity);
    	}else {
            model.addAttribute("product", new Product());
    	}
        return "add-product";
    }
    
    @RequestMapping(path = {"/deleteproduct", "/deleteproduct/{id}"})
    public String deleteProduct(Model model, @PathVariable("id") Optional<Integer> id) throws RecordNotFoundException 
    {
    	p_service.deleteUserById(id.get());
          return "redirect:/admin";
    }
    
    @RequestMapping(path = {"/addamount/{id}"})
    public String addAmountById(Model model, @PathVariable("id") Optional<Long> id) 
                            throws RecordNotFoundException 
    {
    	String redirectUrl = "http://localhost:8081/addamount/"+ id.get();
   	 return "redirect:" + redirectUrl;
    	
    }
    
    @RequestMapping(path = {"/transactions/{id}"})
    public String Transcations(Model model, @PathVariable("id") Optional<Long> id) 
                            throws RecordNotFoundException 
    {
    	String redirectUrl = "http://localhost:8081/transactions/"+ id.get();
    	 return "redirect:" + redirectUrl;
     }
    
    
    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
                            throws RecordNotFoundException 
    {
        service.deleteUserById(id);
        return "redirect:/admin";
    }
 
    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createOrUpdateEmployee(User user) 
    {
        service.createOrUpdateUser(user);
        return "redirect:/admin";
    }
    
    @RequestMapping(path = "/createProduct", method = RequestMethod.POST)
    public String createProduct(Product product) 
    {
        p_service.saveProduct(product);
        return "redirect:/admin";
    }
    
    
    @RequestMapping("/user")
    public String user(Model model,@RequestParam(value = "mobileid", required = false) String mobileid) {
    	
    	String redirectUrl = "http://localhost:8081/user";
     	 return "redirect:" + redirectUrl;
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}








