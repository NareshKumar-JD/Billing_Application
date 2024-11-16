package com.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Entity.Customer;
import com.springboot.Service.CustomerMailService;
import com.springboot.Service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	CustomerService service;
	
	 @Autowired
	    private CustomerMailService customerMailService;

	    @PostMapping("/customer")
	    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
	        try {
	            customerMailService.saveCustomerAndSendEmail(customer);
	            return ResponseEntity.ok("Customer created and email sent successfully!");
	        } catch (RuntimeException e) {
	          	return ResponseEntity.status(400).body("Error: " + e.getMessage());
	        } catch (Exception e) {
	        
	            e.printStackTrace();
	            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
	        }
	    }

	    @GetMapping("/customer")
	    public ResponseEntity<List<Customer>> getAllCustomers() {
	        return ResponseEntity.ok().body(service.getCustomers());
	    }

	    @GetMapping("/customer/{term}")
	    public ResponseEntity<List<Customer>> getCustomersByTerm(@PathVariable String term) {
	        List<Customer> customers = service.getCustomersByTerm(term);
	        if (customers.isEmpty()) {
	            return ResponseEntity.noContent().build(); 
	        }
	        return ResponseEntity.ok().body(customers); 
	    }

	    @PutMapping("/customer/{cus_id}") 
	    public ResponseEntity<Customer> updateCustomer(@PathVariable int cus_id, @RequestBody Customer cus) {
	    	cus.setCus_id(cus_id); 
	        return ResponseEntity.ok().body(service.updateCustomer(cus));
	    }    

	    @DeleteMapping("/customer/{cus_id}")
	    public ResponseEntity<String> deleteCustomer(@PathVariable int cus_id) {
	        service.deleteCusById(cus_id);
	        return ResponseEntity.ok().body("Record has been deleted");
	    }   
	}
