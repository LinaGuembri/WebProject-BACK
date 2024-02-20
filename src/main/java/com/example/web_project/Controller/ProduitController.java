package com.example.web_project.Controller;
import java.util.List;
import javax.validation.Valid;
import com.example.web_project.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.web_project.Entity.Produit;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

   @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produit> createProduit(@Valid @RequestBody Produit produit) {
        Produit savedProduit = produitService.saveProduit(produit);
        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable("id") Long id, @Valid @RequestBody Produit produit) {
        Produit updatedProduit = produitService.updateProduit(id, produit);
        if (updatedProduit != null) {
            return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
