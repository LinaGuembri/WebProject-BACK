package com.example.web_project.Service;

import com.example.web_project.Entity.Category;
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

    private List<Product> compareProducts = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

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

    public int getProductQuantity(String productReference) {
        Product product = productRepository.findByProductReference(productReference);
        if (product != null) {
            return product.getQuantity();
        } else {
            return 0; // Return 0 or any default value if product not found
        }
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
            existingProduct.setQuantity(updatedProduct.getQuantity());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    // New method to add a product to the comparison list
    public boolean addToCompare(Product product) {
        if (compareProducts.contains(product)) {
            return false; // Product already exists in comparison list
        }
        compareProducts.add(product);
        return true; // Product added to comparison list
    }

    // New method to check if a product exists in the comparison list
    public boolean hasProductInCompare(Long productId) {
        for (Product product : compareProducts) {
            if (product.getProductReference().equals(productId)) {
                return true; // Product exists in comparison list
            }
        }
        return false; // Product does not exist in comparison list
    }

    // New method to retrieve the comparison list
    public List<Product> getCompareProducts() {
        return compareProducts;
    }

    public void removeFromCompare(String productId) {
        compareProducts.removeIf(product -> product.getProductReference().equals(productId));
    }
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategory_IdCategory(categoryId);
    }


}
