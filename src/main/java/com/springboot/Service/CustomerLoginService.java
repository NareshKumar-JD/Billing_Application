package com.springboot.Service;

import com.springboot.Entity.Customer;

public interface CustomerLoginService {

	 Customer getCustomerByName(String name);
     boolean authenticateCustomer(String name, String password);
	
}
