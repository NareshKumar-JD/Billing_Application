package com.springboot.Service;

import com.springboot.Entity.Admin;

public interface AdminService {

    Admin getAdminByName(String name);

    boolean authenticateAdmin(String name, String password);
}
