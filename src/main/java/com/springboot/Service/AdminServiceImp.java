package com.springboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.Entity.Admin;
import com.springboot.Repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin getAdminByName(String name) {
        return adminRepository.findByName(name).orElse(null);
    }

    @Override
    public boolean authenticateAdmin(String name, String password) {
        Admin admin = getAdminByName(name);
        if (admin != null && admin.getPassword().equals(password)) {
            return true;  
        }
        return false; 
    }
}
