package com.example.web_project.Service;

import com.example.web_project.Entity.Product;
import com.example.web_project.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        Iterable<Product> productsIterable = productRepository.findAll();
        List<Product> productsList = new ArrayList<>();
        productsIterable.forEach(productsList::add);
        return productsList;
    }


    public Product getProductByReference(String productReference) {
        Optional<Product> optionalProduct = productRepository.findById(productReference);
        return optionalProduct.orElse(null);
    }

    public Product updateProduct(String productReference, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productReference).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setColor(updatedProduct.getColor());
            existingProduct.setBrand(updatedProduct.getBrand());
            existingProduct.setCategory(updatedProduct.getCategory());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

}
