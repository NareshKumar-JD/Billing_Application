package com.springboot.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.Entity.Product;
import com.springboot.Repository.ProductRepository;
import com.springboot.Error.RecordNotFoundException;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new RecordNotFoundException("Product not found: " + name));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProById(Long pro_id) {
        productRepository.findById(pro_id)
                .ifPresentOrElse(
                    product -> productRepository.deleteById(pro_id),
                    () -> { throw new RecordNotFoundException("Product not found: " + pro_id); }
                );
    }

    @Override
    public List<Product> getProductsByStartingLetter(String letter) {
        return productRepository.findByNameStartingWith(letter);
    }

    @Override
    public List<Product> getProductsByPartialName(String partialName) {
        return productRepository.findByNameContaining(partialName);
    }

    @Override
    public List<Product> getProductsByTerm(String term) {
        if (term.length() == 1) {
        	
            return getProductsByStartingLetter(term.toUpperCase());
        } else {
           
            return getProductsByPartialName(term);
        }
    }
}
