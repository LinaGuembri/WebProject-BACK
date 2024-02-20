
package com.example.web_project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.web_project.Entity.Produit;
import com.example.web_project.Repository.ProduitRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

   public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }
  

    public Produit updateProduit(Long id, Produit newProduit) {
        Optional<Produit> existingProduitOptional = produitRepository.findById(id);
        if (existingProduitOptional.isPresent()) {
            Produit existingProduit = existingProduitOptional.get();
            existingProduit.setRefProduit(newProduit.getRefProduit());
            existingProduit.setNom(newProduit.getNom());
            existingProduit.setDescription(newProduit.getDescription());
            existingProduit.setImage(newProduit.getImage());
            existingProduit.setPrix(newProduit.getPrix());
            existingProduit.setQuantite(newProduit.getQuantite());
            return produitRepository.save(existingProduit);
        } else {
            return null;
        }
    }
  
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }


}


