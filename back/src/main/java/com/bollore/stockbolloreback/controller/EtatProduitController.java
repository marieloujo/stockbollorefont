package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.enumeration.EnumProduitEtat;
import com.bollore.stockbolloreback.models.EtatProduit;
import com.bollore.stockbolloreback.models.Produit;
import com.bollore.stockbolloreback.repository.EtatProduitRepository;
import com.bollore.stockbolloreback.repository.EtatRepository;
import com.bollore.stockbolloreback.repository.ProduitRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etat-produit")
@Api(value = "EtatProduit controller",
        description = "Ressources API pour gérer les différents état des produits en stokage à bollore")
@CrossOrigin("*")
@Transactional
public class EtatProduitController {

    @Autowired
    private EtatProduitRepository etatProduitRepository;

    @Autowired
    private ProduitRepository produitRepository;

    private static final String ENTITY_NAME = "stockBollore_EtatProduit";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des etats produit")
    @GetMapping(value = "/list")
    public ResponseEntity<List<EtatProduit>> getAllEtatProduit(){
        return ResponseEntity.status(HttpStatus.OK).body(etatProduitRepository.findAllByOrderByCreatedDateDesc());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir un objet etatProduit par id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<EtatProduit> getEtatProduitById(@PathVariable("id") Long id){

        Optional<EtatProduit> etatProd = etatProduitRepository.findById(id);

        EtatProduit etatProduit = etatProd.get();

        return ResponseEntity.status(HttpStatus.OK).body(etatProduit);
    }

    @ApiOperation(value = "cette ressource permet de d'ajouter un etat a un produit")
    @PostMapping(value = "/creer-etat-produit")
    public ResponseEntity<EtatProduit> createEtatProduit(@Valid @RequestBody EtatProduit etatProduit) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (etatProduit.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        EtatProduit newEtatProduit = etatProduitRepository.save(etatProduit);
        Produit produit = produitRepository.findById(etatProduit.getProduit().getId()).orElse(null);
        if(produit != null) {
            // set the etat
            produit.setEtatActuel(EnumProduitEtat.valueOf(etatProduit.getEtat().getCode()));
            produitRepository.save(produit);
        }
        return ResponseEntity.created(new URI("/etat-produit/creer-etat-produit"+ newEtatProduit.getId())).body(newEtatProduit);
    }

    @ApiOperation(value = "cette ressource permet de modifier l'etat d'un produits")
    @PutMapping(value = "/modifier-etat-produit")
    public ResponseEntity<EtatProduit> updateEtatProduit(@RequestBody EtatProduit etatProduit){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (etatProduit.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!etatProduitRepository.existsById(etatProduit.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        EtatProduit etatProduitToUpdate = etatProduitRepository.saveAndFlush(etatProduit);
        Produit produit = produitRepository.findById(etatProduit.getProduit().getId()).orElse(null);
        if(produit != null) {
            // set the etat
            produit.setEtatActuel(EnumProduitEtat.valueOf(etatProduit.getEtat().getCode()));
            produitRepository.save(produit);
        }

        return ResponseEntity.ok().body(etatProduitToUpdate);

    }

}
