package com.example.web_project.Service;

import com.example.web_project.Entity.Cart;
import com.example.web_project.Entity.Delivery;
import com.example.web_project.Entity.OrderDetail;
import com.example.web_project.Entity.Product;
import com.example.web_project.Repository.CartRepository;
import com.example.web_project.Repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetail checkout(Long userId, Long cartId, Delivery delivery) {
        // Retrieve user's cart
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (!optionalCart.isPresent()) {
            // Handle cart not found
            return null;
        }
        Cart cart = optionalCart.get();

        // Validate cart ownership
        if (!cart.getUser().getId().equals(userId)) {
            // Handle unauthorized access
            return null;
        }

        // Create order detail
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setUser(cart.getUser());
        orderDetail.setOrderAmount(calculateOrderAmount(cart));
        orderDetail.setOrderStatus("Pending"); // Set initial order status
        orderDetail.setDelivery(delivery);

        // Save order detail
        orderDetail = orderDetailRepository.save(orderDetail);

        // Optionally, clear the cart after checkout
        cart.getProducts().clear();
        cartRepository.save(cart);

        return orderDetail;
    }

    // Helper method to calculate the order amount based on the products and their quantities in the cart
    private Double calculateOrderAmount(Cart cart) {
        double totalAmount = 0.0;
        for (Map.Entry<Product, Integer> entry : cart.getProductQuantityMap().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalAmount += product.getPrice() * quantity;
        }
        return totalAmount;
    }


}
