package com.example.web_project.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ElementCollection
    @CollectionTable(name = "cart_product_mapping",
            joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> productQuantityMap = new HashMap<>();



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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProductWithQuantity(Product product, int quantity) {
        // Update quantity if the product already exists in the map
        if (productQuantityMap.containsKey(product)) {
            int existingQuantity = productQuantityMap.get(product);
            productQuantityMap.put(product, existingQuantity + quantity);
        } else {
            // Add the product with the specified quantity
            productQuantityMap.put(product, quantity);
        }
    }
}
