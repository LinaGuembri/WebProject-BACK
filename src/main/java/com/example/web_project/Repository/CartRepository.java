package com.example.web_project.Repository;

import com.example.web_project.Entity.Cart;
import com.example.web_project.Entity.Product;
import com.example.web_project.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long > {
    public List<Cart> findByUser(User user);




}
