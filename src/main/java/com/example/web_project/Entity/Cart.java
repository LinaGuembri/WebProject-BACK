package com.example.web_project.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
    @OneToOne
    private User user;



    public Cart() {

    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public void addProduct(Product product) {
        this.products.add(product);
        product.getCarts().add(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getCarts().remove(this);
    }

    public Cart(User user) {
        this.user = user;
    }
}
