package com.example.web_project.Controller;

import com.example.web_project.Entity.Produit;
import com.example.web_project.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") Long id) {
        Produit produit = produitService.getProduitById(id);
        if (produit != null) {
            return new ResponseEntity<>(produit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") Long id) {
        produitService.deleteProduit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
