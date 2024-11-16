package com.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.Entity.Accountant;
import com.springboot.Service.AccountantService;

@RestController
public class AccountantController {

    @Autowired
    private AccountantService serviceac;

    @PostMapping("/accountant/authenticate")
    public ResponseEntity<String> authenticateAccountant(@RequestBody Accountant accountantRequest) {
      Accountant storedAccountant = serviceac.getAccountantByName(accountantRequest.getName());
        if (storedAccountant == null) {
            return ResponseEntity.badRequest().body("Wrong name given.");
        }
        if (!storedAccountant.getPassword().equals(accountantRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong password given.");
        }

        return ResponseEntity.ok().body("Authentication successful: " + storedAccountant.getName());
    }

}
