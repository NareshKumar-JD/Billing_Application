package com.springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Customer;
import com.springboot.Error.RecordNotFoundException;
import com.springboot.Repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {
	@Autowired
	CustomerRepository repoc;

	@Override
	public Customer save(Customer customer) {
		 return repoc.save(customer);
	}

	@Override
	public Customer getCusByName(String name) {
		 return repoc.findByName(name)
	                .orElseThrow(() -> new RecordNotFoundException("Customer not found: " + name));
	}

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return repoc.findAll();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		  return repoc.save(customer);
	}

	@Override
	public void deleteCusById(int cus_id) {
		   repoc.findById(cus_id)
           .ifPresentOrElse(
               product -> repoc.deleteById(cus_id),
               () -> { throw new RecordNotFoundException("Customer not found: " + cus_id); }
           );
}

	@Override
	public List<Customer> getCustomersByStartingLetter(String letter) {
		 return repoc.findByNameStartingWith(letter);
	}

	@Override
	public List<Customer> getCustomersByPartialName(String partialName) {
		 return repoc.findByNameContaining(partialName);
	}

	@Override
	public List<Customer> getCustomersByTerm(String term) {
	      if (term.length() == 1) {
	        	
	            return getCustomersByStartingLetter(term.toUpperCase());
	        } else {
	           
	            return getCustomersByPartialName(term);
	        }
	}
	
}