package com.example.web_project.Controller;

import com.example.web_project.Entity.Product;
import com.example.web_project.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{productReference}/quantity")
    public ResponseEntity<Integer> getProductQuantity(@PathVariable String productReference) {
        int quantity = productService.getProductQuantity(productReference);
        return ResponseEntity.ok(quantity);
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
    public ResponseEntity<Void> removeFromCompare(@PathVariable String productId) {
        productService.removeFromCompare(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no products found
        } else {
            return ResponseEntity.ok(products); // Return products if found
        }
    }


    @GetMapping("/compare")
    public ResponseEntity<List<Product>> getComparedProducts() {
        List<Product> comparedProducts = productService.getCompareProducts();
        if (comparedProducts != null && !comparedProducts.isEmpty()) {
            return ResponseEntity.ok(comparedProducts);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }



}
