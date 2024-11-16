package com.springboot.Service;

import java.util.List;
import com.springboot.Entity.Product;

public interface ProductService {
    Product save(Product product);
    Product getProByName(String name);
    List<Product> getProducts();
    Product updateProduct(Product product);
    void deleteProById(Long pro_id);
    List<Product> getProductsByStartingLetter(String letter);
    List<Product> getProductsByPartialName(String partialName);
    
    List<Product> getProductsByTerm(String term);
}
