package com.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Entity.Customer;
import com.springboot.Service.CustomerLoginService;

@RestController
public class CustomerLoginController {
@Autowired
CustomerLoginService servicecu;

	@PostMapping("/customer/authenticate")
    public ResponseEntity<String> authenticateCustomer(@RequestBody Customer customerRequest) {
      Customer storedCustomer = servicecu.getCustomerByName(customerRequest.getName());
        if (storedCustomer == null) {
            return ResponseEntity.badRequest().body("Wrong name given.");
        }
        if (!storedCustomer.getPassword().equals(customerRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong password given.");
        }
        
        return ResponseEntity.ok().body("Authentication successful: " + storedCustomer.getName());
    }
}

