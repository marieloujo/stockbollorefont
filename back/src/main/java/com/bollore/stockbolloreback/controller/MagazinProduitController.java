package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.MagazinProduit;
import com.bollore.stockbolloreback.repository.EtatRepository;
import com.bollore.stockbolloreback.repository.MagazinProduitRepository;
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
@RequestMapping("/magazin-produit")
@Api(value = "MagasinProduit controller",
        description = "Ressources API pour gérer le stockage de produits dans les magasins à bollore")
@CrossOrigin("*")
@Transactional
public class MagazinProduitController {

    @Autowired
    private MagazinProduitRepository magazinProduitRepository;

    private static final String ENTITY_NAME = "stockBollore_MagazinProduit";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des magasin produit")
    @GetMapping(value = "/list")
    public ResponseEntity<List<MagazinProduit>> getAllMagazinProduit(){
        return ResponseEntity.status(HttpStatus.OK).body(magazinProduitRepository.findAllByOrderByCreatedDateDesc());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir un objet magasinProduit par id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<MagazinProduit> getMagazinProduitById(@PathVariable("id") Long id){

        Optional<MagazinProduit> magProd = magazinProduitRepository.findById(id);

        MagazinProduit magazinProduit = magProd.get();

        return ResponseEntity.status(HttpStatus.OK).body(magazinProduit);
    }

    @ApiOperation(value = "cette ressource permet de d'ajouter un produit dans un magasin")
    @PostMapping(value = "/creer-magazin-produit")
    public ResponseEntity<MagazinProduit> createMagazinProduit(@Valid @RequestBody MagazinProduit magazinProduit) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (magazinProduit.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        MagazinProduit newMagazinProduit = magazinProduitRepository.save(magazinProduit);

        return ResponseEntity.created(new URI("/magazin/creer-magazin-produit"+ newMagazinProduit.getId())).body(newMagazinProduit);
    }

    @ApiOperation(value = "cette ressource permet de modifier l'etat d'un produits")
    @PutMapping(value = "/modifier-magazin-produit")
    public ResponseEntity<MagazinProduit> updateMagazinProduit(@RequestBody MagazinProduit magazinProduit){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (magazinProduit.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!magazinProduitRepository.existsById(magazinProduit.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        MagazinProduit magazinProduitToUpdate = magazinProduitRepository.saveAndFlush(magazinProduit);

        return ResponseEntity.ok().body(magazinProduitToUpdate);

    }

}
