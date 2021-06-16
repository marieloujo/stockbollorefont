package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.Etat;
import com.bollore.stockbolloreback.models.EtatProduit;
import com.bollore.stockbolloreback.repository.EtatProduitRepository;
import com.bollore.stockbolloreback.repository.EtatRepository;
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
@RequestMapping("/etat")
@Api(value = "Etat controller",
        description = "Ressources API pour gérer les état des produits en stokage à bollore")
@CrossOrigin("*")
@Transactional
public class EtatController {

    @Autowired
    private EtatRepository etatRepository;

    @Autowired
    private EtatProduitRepository etatProduitRepository;

    private static final String ENTITY_NAME = "stockBollore_Etat";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des états des produits")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Etat>> getAllEtat(){
        return ResponseEntity.status(HttpStatus.OK).body(etatRepository.findAll());
    }

    @ApiOperation(value = "cette ressource permet un etat par son code")
    @GetMapping(value = "/get-etat-byCode/{code}")
    public ResponseEntity<Etat> getEtatByCode(@PathVariable("code") String code){
        return ResponseEntity.status(HttpStatus.OK).body(etatRepository.findByCode(code));
    }

    @ApiOperation(value = "cette ressource permet d'obtenir un etat par son id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Etat> getEtatById(@PathVariable("id") Long id){

        Optional<Etat> et = etatRepository.findById(id);

        Etat etat = et.get();

        return ResponseEntity.status(HttpStatus.OK).body(etat);
    }

    @ApiOperation(value = "cette ressource permet de créer l'etat des produits")
    @PostMapping(value = "/creer-etat")
    public ResponseEntity<Etat> createEtat(@Valid @RequestBody Etat etat) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (etat.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Etat newEtat = etatRepository.save(etat);

        return ResponseEntity.created(new URI("/etat/creer-etat"+ newEtat.getId())).body(newEtat);
    }

    @ApiOperation(value = "cette ressource permet de modifier l'etat d'un produits")
    @PutMapping(value = "/modifier-etat")
    public ResponseEntity<Etat> updateEtat(@RequestBody Etat etat){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (etat.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!etatRepository.existsById(etat.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Etat etatToUpdate = etatRepository.saveAndFlush(etat);

        return ResponseEntity.ok().body(etatToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer l'etat d'un produits")
    @DeleteMapping(value = "/supprimer-etat/{id}")
    public ResponseEntity<Void> deleteEtat(@PathVariable("id") Long id){
        List<EtatProduit> listProduitByEtatId;
        listProduitByEtatId = etatProduitRepository.findByEtat_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listProduitByEtatId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!etatRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        etatRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }




}
