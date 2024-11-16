package com.springboot.Service;

import com.springboot.Entity.Customer;
import com.springboot.Repository.CustomerMailRepository;
import com.springboot.Utility.CustomerMailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerMailServiceImp implements CustomerMailService {

    @Autowired
    private CustomerMailRepository customerMailRepository;

    @Autowired
    private CustomerMailUtility customerMailUtility;
   
    @Override
    public void saveCustomerAndSendEmail(Customer customer) {
        Optional<Customer> existingCustomer = customerMailRepository.findByEmail(customer.getEmail());
        if (existingCustomer.isPresent()) {
            throw new RuntimeException("Email already exists: " + customer.getEmail());
        }
        System.out.println("Saving customer: Name = " + customer.getName() + ", Email = " + customer.getEmail());
        customerMailRepository.save(customer);
        String subject = "Welcome to Our Service!";
        String message = String.format(
            "Dear %s,\n\nThank you for registering with us.\n\nHere are your details:\nName: %s\nPassword: %s\nEmail: %s\nPhone: %d\nAddress: %s\n\nBest regards,\nOur Team",
            customer.getName(), customer.getName(), customer.getPassword(), customer.getEmail(), customer.getPhone(), customer.getAddress()
        );
        customerMailUtility.sendEmail(customer.getEmail(), subject, message);
    }
}
    
    