package com.example.web_project.Controller;

import com.example.web_project.Entity.Product;
import com.example.web_project.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productReference}")
    public ResponseEntity<Product> getProductByReference(@PathVariable String productReference) {
        Product product = productService.getProductByReference(productReference);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // New endpoint to add a product to the comparison list
    @PostMapping("/compare/add")
    public ResponseEntity<String> addToCompare(@RequestBody Product product) {
        boolean added = productService.addToCompare(product);
        if (added) {
            return ResponseEntity.ok("Product added to comparison list successfully");
        } else {
            return ResponseEntity.badRequest().body("Product already exists in comparison list");
        }
    }

    // New endpoint to check if a product exists in the comparison list
    @GetMapping("/compare/check/{productId}")
    public ResponseEntity<Boolean> hasProductInCompare(@PathVariable Long productId) {
        boolean exists = productService.hasProductInCompare(productId);
        return ResponseEntity.ok(exists);
    }

    // New endpoint to retrieve the comparison list
    @GetMapping("/compare/list")
    public ResponseEntity<List<Product>> getCompareProducts() {
        List<Product> compareProducts = productService.getCompareProducts();
        return ResponseEntity.ok(compareProducts);
    }

    @DeleteMapping("/compare/{productId}")
    public ResponseEntity<Void> removeFromCompare(@PathVariable Long productId) {
        productService.removeFromCompare(productId);
        return ResponseEntity.noContent().build();
    }
    // New endpoint to get products by category
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        List<Product> products = productService.getProductsByCategory(categoryName);
        return ResponseEntity.ok(products);
    }


}
