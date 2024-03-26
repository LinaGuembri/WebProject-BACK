package com.example.web_project.Service;

import com.example.web_project.Entity.Cart;
import com.example.web_project.Entity.Product;
import com.example.web_project.Entity.User;
import com.example.web_project.Repository.CartRepository;
import com.example.web_project.Repository.ProductRepository;
import com.example.web_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartDao;

    @Autowired
    private ProductRepository productDao;

    @Autowired
    private UserRepository userDao;


    public void deleteCartItem(Long cartItemId) {
        cartDao.deleteById(cartItemId);
    }

    public Cart addToCart(String productId, Long userId) {
        Product product = productDao.findById(productId).orElse(null);
        User user = userDao.findById(userId).orElse(null); // Retrieve existing user

        if (product == null || user == null) {
            // Handle product or user not found
            return null;
        }

        List<Cart> carts = cartDao.findByUser(user);

        // Choose one cart or handle multiple carts as needed
        Cart cart;
        if (!carts.isEmpty()) {
            cart = carts.get(0); // Choose the first cart for simplicity
        } else {
            cart = new Cart(user); // Create a new cart if no cart exists for the user
        }

        // Add the product to the cart
        cart.addProduct(product);

        // Save the cart
        return cartDao.save(cart);
    }

}
