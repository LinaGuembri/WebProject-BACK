package com.example.web_project.Controller;

import com.example.web_project.Entity.Cart;
import com.example.web_project.Entity.User;
import com.example.web_project.Service.CartService;
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
    /*
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam String productId, @RequestBody User user) {
        Cart cart = cartService.addToCart(productId, user.getId());
        if (cart != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        } else {
            return ResponseEntity.badRequest().build();
        }
    } */

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart( @RequestParam Long userId, @RequestParam String productId) {
        Cart cart = cartService.addToCart( userId, productId);
        if (cart != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartId) {
        cartService.deleteCartItem(cartId);
        return ResponseEntity.noContent().build();
    }

}
