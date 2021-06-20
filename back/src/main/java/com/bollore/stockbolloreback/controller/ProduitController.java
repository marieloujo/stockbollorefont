package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.enumeration.ProduitStatus;
import com.bollore.stockbolloreback.models.DemandeProduit;
import com.bollore.stockbolloreback.models.EtatProduit;
import com.bollore.stockbolloreback.models.MagazinProduit;
import com.bollore.stockbolloreback.models.Produit;
import com.bollore.stockbolloreback.repository.DemandeProduitRepository;
import com.bollore.stockbolloreback.repository.EtatProduitRepository;
import com.bollore.stockbolloreback.repository.MagazinProduitRepository;
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
@RequestMapping("/produit")
@Api(value = "Produit controller",
        description = "Ressources API pour gérer les produits de stokage de bollore")
@CrossOrigin("*")
@Transactional
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private MagazinProduitRepository magazinProduitRepository;

    @Autowired
    private EtatProduitRepository etatProduitRepository;

    @Autowired
    private DemandeProduitRepository demandeProduitRepository;

    private static final String ENTITY_NAME = "stockBollore_Produit";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des produits")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Produit>> getAllProduits(){
        return ResponseEntity.status(HttpStatus.OK).body(produitRepository.findAll());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir un produit par id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") Long id){

        Optional<Produit> prod = produitRepository.findById(id);

        Produit produit = prod.get();

        return ResponseEntity.status(HttpStatus.OK).body(produit);
    }


    @ApiOperation(value = "cette ressource permet d'ajouter un produit")
    @PostMapping(value = "/creer-produit")
    public ResponseEntity<Produit> createProduit(@Valid @RequestBody Produit produit) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (produit.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // default status product
        produit.setStatus(ProduitStatus.EN_STOCK);
        Produit newProduit = produitRepository.save(produit);

        return ResponseEntity.created(new URI("/produit/creer-produit"+ newProduit.getId())).body(newProduit);
    }

    @ApiOperation(value = "cette ressource permet de modifier un produit")
    @PutMapping(value = "/modifier-produit")
    public ResponseEntity<Produit> updateProduit(@RequestBody Produit produit){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un produit qui n'existe pas
        if (produit.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!produitRepository.existsById(produit.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Produit oldProduit = produitRepository.findById(produit.getId()).orElse(null);
        if(oldProduit != null) {
            // ne pas permettre de changer le status à la modification du produit
            produit.setStatus(oldProduit.getStatus());
        }
        Produit produitToUpdate = produitRepository.saveAndFlush(produit);

        return ResponseEntity.ok().body(produitToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer un produit")
    @DeleteMapping(value = "/supprimer-produit/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") Long id){

        List<MagazinProduit> magazinProduitListByProduitId;
        List<EtatProduit> etatProduitListByProduitId;
        List<DemandeProduit> demandeProduitListByProduitId;

        magazinProduitListByProduitId = magazinProduitRepository.findByProduit_Id(id);
        etatProduitListByProduitId = etatProduitRepository.findByProduit_Id(id);
        demandeProduitListByProduitId = demandeProduitRepository.findByProduit_Id(id);

        if (id == null || !produitRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (magazinProduitListByProduitId.size() > 1 || etatProduitListByProduitId.size() > 1 || demandeProduitListByProduitId.size() >= 1){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (magazinProduitListByProduitId.size() == 1){
            if (!magazinProduitRepository.existsById(magazinProduitListByProduitId.get(0).getId()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            else {
                Optional<MagazinProduit> theMagasinProduit = magazinProduitRepository.findById(magazinProduitListByProduitId.get(0).getId());
                theMagasinProduit.ifPresent(magazinProduit -> magazinProduitRepository.deleteById(magazinProduit.getId()));
            }
        }

        if (magazinProduitListByProduitId.size() == 1){
            if (!etatProduitRepository.existsById(etatProduitListByProduitId.get(0).getId()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            else {
                Optional<EtatProduit> theEtatProduit = etatProduitRepository.findById(etatProduitListByProduitId.get(0).getId());
                theEtatProduit.ifPresent(etatProduit -> etatProduitRepository.deleteById(etatProduit.getId()));
            }
        }

        Optional<Produit> theProduit = produitRepository.findById(id);

        produitRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }


}
