package com.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Entity.Product;
import com.springboot.Service.ProductService;

@RestController
public class ProductController {

    @Autowired
    ProductService services;

    @PostMapping("/product")
    public ResponseEntity<Product> save(@RequestBody Product pro) {
        return ResponseEntity.ok().body(services.save(pro));    
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(services.getProducts());
    }

    // Call the service method directly to handle logic
    @GetMapping("/product/{term}")
    public ResponseEntity<List<Product>> getProductsByTerm(@PathVariable String term) {
        List<Product> products = services.getProductsByTerm(term);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok().body(products); 
    }

    @PutMapping("/product/{pro_id}")  // Updating only by id
    public ResponseEntity<Product> updateProduct(@PathVariable Long pro_id, @RequestBody Product pro) {
        pro.setPro_id(pro_id); 
        return ResponseEntity.ok().body(services.updateProduct(pro));
    }    

    @DeleteMapping("/product/{pro_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long pro_id) {
        services.deleteProById(pro_id);
        return ResponseEntity.ok().body("Record has been deleted");
    }   
}
