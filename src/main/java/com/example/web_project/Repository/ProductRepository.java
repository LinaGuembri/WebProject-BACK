package com.example.web_project.Repository;

import com.example.web_project.Entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    public List<Product> findAll(Pageable pageable);

    List<Product> findByCategory_IdCategory(Long categoryId);

}
