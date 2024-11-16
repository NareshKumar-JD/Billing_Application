package com.springboot.Service;

import java.util.List;
import java.util.Optional;

import com.springboot.Entity.Customer;

public interface CustomerService {
	
	Customer save(Customer customer);
    Customer getCusByName(String name);
    List<Customer> getCustomers();
    Customer updateCustomer(Customer customer);
    void deleteCusById(int cus_id);
    List<Customer> getCustomersByStartingLetter(String letter);
    List<Customer> getCustomersByPartialName(String partialName);
 	List<Customer> getCustomersByTerm(String term);

}
