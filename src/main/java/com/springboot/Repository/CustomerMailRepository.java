package com.springboot.Repository;

import com.springboot.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerMailRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email); 
}
