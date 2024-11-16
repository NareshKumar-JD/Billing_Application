package com.springboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Customer;
import com.springboot.Repository.CustomerLoginRepository;

@Service
public class CustomerLoginServiceImp implements CustomerLoginService {
@Autowired
CustomerLoginRepository repocu;

    @Override
    public Customer getCustomerByName(String name) {
        return repocu.findByName(name).orElse(null);
    }

    @Override
    public boolean authenticateCustomer(String name, String password) {
        Customer customer = getCustomerByName(name);
        if (customer != null && customer.getPassword().equals(password)) {
            return true;  
        }
        return false; 
    }
}
