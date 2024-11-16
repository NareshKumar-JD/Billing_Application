package com.springboot.Service;

import com.springboot.Entity.Accountant;

public interface AccountantService {

    Accountant getAccountantByName(String name);

    boolean authenticateAccountant(String name, String password);
}
