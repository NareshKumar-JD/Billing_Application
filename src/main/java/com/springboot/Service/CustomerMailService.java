package com.springboot.Service;

import com.springboot.Entity.Customer;

public interface CustomerMailService {
    void saveCustomerAndSendEmail(Customer customer);
}
