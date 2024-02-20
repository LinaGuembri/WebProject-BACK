package com.example.web_project.Service;


import com.example.web_project.Entity.Produit;
import com.example.web_project.ProduitRepository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;
    public Produit getProduitById(Long id) {
        Optional<Produit> produitOptional = produitRepository.findById(id);
        return produitOptional.orElse(null);
    }
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
