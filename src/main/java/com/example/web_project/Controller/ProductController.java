package com.example.web_project.Controller;

import com.example.web_project.Service.ProductService;
import com.example.web_project.Entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/{productReference}")
    public ResponseEntity<Product> getProductByReference(@PathVariable("productReference") String productReference) {
        Product product = productService.getProductByReference(productReference);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{productReference}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productReference") String productReference, @Valid @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productReference, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productReference}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productReference") String productReference) {
        productService.deleteProduct(productReference);
        return ResponseEntity.noContent().build();
    }
}
