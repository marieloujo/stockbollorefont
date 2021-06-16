package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.DemandeProduit;
import com.bollore.stockbolloreback.repository.DemandeProduitRepository;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demande-produit")
@Api(value = "DemandeProduit controller",
        description = "Ressources API pour gérer les différentes demande concernant les produits à bollore")
@CrossOrigin("*")
@Transactional
public class DemandeProduitController {

    @Autowired
    private DemandeProduitRepository demandeProduitRepository;

    private static final String ENTITY_NAME = "stockBollore_DemandeProduit";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des demandes produit")
    @GetMapping(value = "/list")
    public ResponseEntity<List<DemandeProduit>> getAllDemandeProduit(){
        return ResponseEntity.status(HttpStatus.OK).body(demandeProduitRepository.findAll());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir le created By de demandeProduit")
    @GetMapping(value = "/demande-produit-createdBy/{id}")
    public ResponseEntity<List<String>> getDemandeProduitCreatedBy(@PathVariable("id") Long id){

        List<String> demandeurEtDate = new ArrayList<>();

        Optional<DemandeProduit> dmdProd = demandeProduitRepository.findById(id);

        DemandeProduit demandeProduit = dmdProd.get();

        demandeurEtDate.add(demandeProduit.getCreatedBy());
        demandeurEtDate.add(demandeProduit.getCreatedDate().toString());

        return ResponseEntity.status(HttpStatus.OK).body(demandeurEtDate);
    }

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des demandes produit par ordre décoissant")
    @GetMapping(value = "/list/desc-create-date")
    public ResponseEntity<List<DemandeProduit>> getAllDemandeProduitDesByCreateDate(){
        //return ResponseEntity.status(HttpStatus.OK).body(demandeProduitRepository.findTop10ByOrderByCreatedDateDesc());
        return ResponseEntity.status(HttpStatus.OK).body(demandeProduitRepository.findAllByLivrerIsFalseOrderByCreatedDateDesc());
    }

    @ApiOperation(value = "cette ressource permet d'ajouter des produits a une demande")
    @PostMapping(value = "/creer-demande-produit")
    public ResponseEntity<DemandeProduit> createDemandeProduit(@Valid @RequestBody DemandeProduit demandeProduit) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (demandeProduit.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DemandeProduit newDemandeProduit = demandeProduitRepository.save(demandeProduit);

        return ResponseEntity.created(new URI("/demande-produit/creer-demande-produit"+ newDemandeProduit.getId())).body(newDemandeProduit);
    }

    @ApiOperation(value = "cette ressource permet de modifier une demande ayant rapport a un produit")
    @PutMapping(value = "/modifier-demande-produit")
    public ResponseEntity<DemandeProduit> updateDemandeProduit(@RequestBody DemandeProduit demandeProduit){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (demandeProduit.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!demandeProduitRepository.existsById(demandeProduit.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DemandeProduit demandeProduitToUpdate = demandeProduitRepository.saveAndFlush(demandeProduit);

        return ResponseEntity.ok().body(demandeProduitToUpdate);

    }

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des demandes produit contenu entre deux dates")
    @GetMapping(value = "/list/between-created-date")
    public ResponseEntity<List<DemandeProduit>> getAllDemandeProduitBetweenCreateDate(@RequestParam LocalDate startDate,@RequestParam("data02") LocalDate endDate){

        Instant instant01 = startDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant instant02 = endDate != null ? endDate.atTime(LocalTime.of(23, 59, 59)).toInstant(ZoneOffset.UTC)
                : startDate.atTime(LocalTime.of(23, 59, 59)).toInstant(ZoneOffset.UTC);

        return ResponseEntity.status(HttpStatus.OK).body(demandeProduitRepository.findAllByCreatedByIsBetween(instant01, instant02));
    }

}
