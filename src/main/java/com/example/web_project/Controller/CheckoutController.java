package com.example.web_project.Controller;

import com.example.web_project.Entity.Delivery;
import com.example.web_project.Entity.OrderDetail;
import com.example.web_project.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/process")
    public ResponseEntity<OrderDetail> checkout(@RequestParam Long userId, @RequestParam Long cartId, @RequestBody Delivery delivery) {
        OrderDetail orderDetail = orderDetailService.checkout(userId, cartId, delivery);
        if (orderDetail != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDetail);
        } else {
            return ResponseEntity.badRequest().build(); // Handle checkout failure
        }
    }


    @GetMapping("/orders/{userId}")
    public List<OrderDetail> getOrdersByUserId(@PathVariable Long userId) {
        return orderDetailService.getOrdersByUserId(userId);
    }

}
