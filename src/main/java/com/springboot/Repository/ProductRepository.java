package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.Entity.Product;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    void deleteById(Long pro_id);
    List<Product> findByNameStartingWith(String letter);
    List<Product> findByNameContaining(String partialName);  
    
}
