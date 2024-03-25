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

    public void deleteCartItem(Long cartId) {
        cartDao.deleteById(cartId);
    }
    /*
    public Cart addToCart(String productId, User user) {
        Product product = productDao.findById(productId).get();


        List<Cart> cartList = cartDao.findByUser(user);
        List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductReference() == productId).collect(Collectors.toList());

        if(filteredList.size() > 0) {
            return null;
        }

        if(product != null && user != null) {
            Cart cart = new Cart(product, user);
            return cartDao.save(cart);
        }

        return null;
    } */
    public Cart addToCart( Long userId, String productId) {
        Product product = productDao.findById(productId).orElse(null);
        User user = userDao.findById(userId).orElse(null); // Retrieve existing user

        if (product == null || user == null) {
            // Handle product or user not found
            return null;
        }

        List<Cart> existingCartItems = cartDao.findByUserAndProduct(user, product); // Corrected parameter order
        if (!existingCartItems.isEmpty()) {
            return existingCartItems.get(0);
        }

        Cart cart = new Cart(product, user);
        return cartDao.save(cart);
    }

    public List<Cart> getCartDetails(User user) {
        return cartDao.findByUser(user);
    }
}
