package com.example.web_project.Service;

import com.example.web_project.Entity.Product;
import com.example.web_project.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String productReference, Product newProduct) {
        Product existingProduct = productRepository.findByProductReference(productReference);
        if (existingProduct != null) {
            existingProduct.setName(newProduct.getName());
            existingProduct.setDescription(newProduct.getDescription());
            existingProduct.setImage(newProduct.getImage());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setColor(newProduct.getColor());
            existingProduct.setBrand(newProduct.getBrand());
            existingProduct.setCategory(newProduct.getCategory());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    public void deleteProduct(String productReference) {
        productRepository.deleteByProductReference(productReference);
    }

    public Product getProductByReference(String productReference) {
        return productRepository.findByProductReference(productReference);
    }
}
