package com.springboot.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    Optional<Customer> findByName(String name);
    void deleteById(int cus_id);
    List<Customer> findByNameStartingWith(String letter);
    List<Customer> findByNameContaining(String partialName); 
	
}
