package com.example.web_project.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.web_project.Entity.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
