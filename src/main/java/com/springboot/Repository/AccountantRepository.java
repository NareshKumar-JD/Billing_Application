package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.Entity.Accountant;

import java.util.Optional;

@Repository
public interface AccountantRepository extends JpaRepository<Accountant, Long> {

    Optional<Accountant> findByName(String name);
}
