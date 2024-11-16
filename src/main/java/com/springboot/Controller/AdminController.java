package com.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.Entity.Admin;
import com.springboot.Service.AdminService;

@RestController
public class AdminController {

    @Autowired
    private AdminService servicea;

    @PostMapping("/admin/authenticate")
    public ResponseEntity<String> authenticateAdmin(@RequestBody Admin adminRequest) {
      Admin storedAdmin = servicea.getAdminByName(adminRequest.getName());
        if (storedAdmin == null) {
            return ResponseEntity.badRequest().body("Wrong name given.");
        }
        if (!storedAdmin.getPassword().equals(adminRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong password given.");
        }
        
        return ResponseEntity.ok().body("Authentication successful: " + storedAdmin.getName());
    }
}
