package com.example.web_project.Service;

import com.example.web_project.Entity.Cart;
import com.example.web_project.Entity.Delivery;
import com.example.web_project.Entity.OrderDetail;
import com.example.web_project.Entity.Product;
import com.example.web_project.Repository.CartRepository;
import com.example.web_project.Repository.DeliveryRepository;
import com.example.web_project.Repository.OrderDetailRepository;
import com.example.web_project.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    ProductRepository productRepository;
    /*
    public OrderDetail checkout(Long userId, Long cartId, Delivery delivery) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (!optionalCart.isPresent()) {
            return null; // Handle cart not found
        }
        Cart cart = optionalCart.get();

        // Validate cart ownership
        if (!cart.getUser().getId().equals(userId)) {
            return null; // Handle unauthorized access
        }

        // Ensure delivery information is provided
        if (delivery == null || delivery.getAddress() == null || delivery.getContactNumber() == null) {
            return null; // Handle incomplete delivery information
        }

        // Create order detail
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setUser(cart.getUser());
        orderDetail.setOrderAmount(calculateOrderAmount(cart));
        orderDetail.setOrderStatus("Pending");

        // Save or update delivery information
        Delivery savedDelivery = deliveryRepository.save(delivery);
        orderDetail.setDelivery(savedDelivery); // Associate the saved delivery

        // Save order detail
        orderDetail = orderDetailRepository.save(orderDetail);

        // Optionally, clear the cart after checkout
        cart.getProductQuantityMap().clear();
        cartRepository.save(cart);

        return orderDetail;
    } */

    public OrderDetail checkout(Long userId, Long cartId, Delivery delivery) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (!optionalCart.isPresent()) {
            return null; // Handle cart not found
        }
        Cart cart = optionalCart.get();

        // Validate cart ownership
        if (!cart.getUser().getId().equals(userId)) {
            return null; // Handle unauthorized access
        }

        // Ensure delivery information is provided
        if (delivery == null || delivery.getAddress() == null || delivery.getContactNumber() == null) {
            return null; // Handle incomplete delivery information
        }

        // Create order detail
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setUser(cart.getUser());
        orderDetail.setOrderAmount(calculateOrderAmount(cart));
        orderDetail.setOrderStatus("Pending");

        // Save or update delivery information
        Delivery savedDelivery = deliveryRepository.save(delivery);
        orderDetail.setDelivery(savedDelivery); // Associate the saved delivery

        // Save order detail
        orderDetail = orderDetailRepository.save(orderDetail);

        // Update product quantities in the stock
        for (Map.Entry<Product, Integer> entry : cart.getProductQuantityMap().entrySet()) {
            Product product = entry.getKey();
            Integer quantityOrdered = entry.getValue();
            int remainingStock = product.getQuantity() - quantityOrdered;
            product.setQuantity(remainingStock);
            productRepository.save(product); // Update product quantity in the database
        }

        // Optionally, clear the cart after checkout
        cart.getProductQuantityMap().clear();
        cartRepository.save(cart);

        return orderDetail;
    }

    private Double calculateOrderAmount(Cart cart) {
        double totalAmount = 0.0;
        for (Map.Entry<Product, Integer> entry : cart.getProductQuantityMap().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalAmount += product.getPrice() * quantity;
        }
        return totalAmount;
    }

    public List<OrderDetail> getOrdersByUserId(Long userId) {
        return orderDetailRepository.findByUserId(userId);
    }

}
