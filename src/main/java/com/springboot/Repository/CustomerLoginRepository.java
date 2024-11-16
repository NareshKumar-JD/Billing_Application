package com.springboot.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.Customer;

public interface CustomerLoginRepository extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByName(String name);
}

