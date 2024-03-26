package com.example.web_project.Controller;

import com.example.web_project.Entity.Cart;
import com.example.web_project.Entity.Product;
import com.example.web_project.Service.CartService;
import com.example.web_project.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        Cart cart = cartService.findById(cartId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.findAllCarts();
        return ResponseEntity.ok(carts);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart( @RequestParam Long userId, @RequestParam String productId) {
        Cart cart = cartService.addToCart( productId, userId);
        if (cart != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{cartId}/product/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long cartId, @PathVariable String productId) {
        cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{productReference}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productReference, @RequestBody Product updatedProduct) {
        Product updated = productService.updateProduct(productReference, updatedProduct);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
