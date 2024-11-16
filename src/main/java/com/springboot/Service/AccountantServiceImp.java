package com.springboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.Entity.Accountant;
import com.springboot.Repository.AccountantRepository;

@Service
public class AccountantServiceImp implements AccountantService {

    @Autowired
    private AccountantRepository accountantRepository;

    @Override
    public Accountant getAccountantByName(String name) {
        return accountantRepository.findByName(name).orElse(null);
    }

    @Override
    public boolean authenticateAccountant(String name, String password) {
        Accountant accountant = getAccountantByName(name);
        if (accountant != null && accountant.getPassword().equals(password)) {
            return true; 
        }
        return false; 
    }
}
