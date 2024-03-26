package com.example.web_project.Service;

import com.example.web_project.Entity.Cart;
import com.example.web_project.Entity.Product;
import com.example.web_project.Entity.User;
import com.example.web_project.Repository.CartRepository;
import com.example.web_project.Repository.ProductRepository;
import com.example.web_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartDao;

    @Autowired
    private ProductRepository productDao;

    @Autowired
    private UserRepository userDao;



    public Cart addToCart(String productId, Long userId) {
        Product product = productDao.findById(productId).orElse(null);
        User user = userDao.findById(userId).orElse(null);

        if (product == null || user == null) {
            return null;
        }

        List<Cart> carts = cartDao.findByUser(user);
        Cart cart;
        if (!carts.isEmpty()) {
            cart = carts.get(0);
        } else {
            cart = new Cart(user);
        }
        cart.addProduct(product);
        return cartDao.save(cart);
    }

    public void deleteCartItem(Long cartItemId) {
        cartDao.deleteById(cartItemId);
    }

    public void removeProductFromCart(Long cartId, String productId) {
        Cart cart = cartDao.findById(cartId).orElse(null);
        if (cart != null) {
            Product productToRemove = null;
            for (Product product : cart.getProducts()) {
                if (product.getProductReference().equals(productId)) {
                    productToRemove = product;
                    break;
                }
            }
            if (productToRemove != null) {
                cart.removeProduct(productToRemove);
                cartDao.save(cart);
            }
        }
    }

    public Cart findById(Long cartId) {
        return cartDao.findById(cartId).orElse(null);
    }

    public List<Cart> findAllCarts() {
        Iterable<Cart> iterable = cartDao.findAll();
        List<Cart> carts = new ArrayList<>();
        iterable.forEach(carts::add);
        return carts;
    }

}
