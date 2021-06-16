package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.Magazin;
import com.bollore.stockbolloreback.models.MagazinProduit;
import com.bollore.stockbolloreback.models.Produit;
import com.bollore.stockbolloreback.repository.MagazinProduitRepository;
import com.bollore.stockbolloreback.repository.MagazinRepository;
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
@RequestMapping("/magazin")
@Api(value = "Magazin controller",
        description = "Ressources API pour gérer les magazins de stokage de bollore")
@CrossOrigin("*")
@Transactional
public class MagazinController {

    @Autowired
    private MagazinRepository magazinRepository;

    @Autowired
    private MagazinProduitRepository magazinProduitRepository;

    private static final String ENTITY_NAME = "stockBollore_Magazin";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des magazins")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Magazin>> getAllMagazins(){
        return ResponseEntity.status(HttpStatus.OK).body(magazinRepository.findAll());
    }

    /*public ResponseEntity<Magazin> createMagazin(@Valid @RequestBody Magazin magazin) throws URISyntaxException {
        if (magazin.getId() != null) {
            throw new BadRequestAlertException("A new magazin cannot already have an ID", ENTITY_NAME, "idexists");
        }
    }*/

    @ApiOperation(value = "cette ressource permet d'obtenir un magasin par son id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Magazin> getMagazinById(@PathVariable("id") Long id){

        Optional<Magazin> mgz = magazinRepository.findById(id);

        Magazin magazin = mgz.get();

        return ResponseEntity.status(HttpStatus.OK).body(magazin);
    }

    @ApiOperation(value = "cette ressource permet d'ajouter un magazin")
    @PostMapping(value = "/creer-magazin")
    public ResponseEntity<Magazin> createMagazin(@Valid @RequestBody Magazin magazin) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (magazin.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Magazin newMagazin = magazinRepository.save(magazin);

        return ResponseEntity.created(new URI("/magazin/creer-magazin"+ newMagazin.getId())).body(newMagazin);
    }

    @ApiOperation(value = "cette ressource permet de modifier un magazin")
    @PutMapping(value = "/modifier-magazin")
    public ResponseEntity<Magazin> updateMagazin(@RequestBody Magazin magazin){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (magazin.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!magazinRepository.existsById(magazin.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Magazin magazinToUpdate = magazinRepository.saveAndFlush(magazin);

        return ResponseEntity.ok().body(magazinToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer un magazin")
    @DeleteMapping(value = "/supprimer-magazin/{id}")
    public ResponseEntity<Void> deleteMagazin(@PathVariable("id") Long id){
        List<MagazinProduit> listMagazinProduitByMagazinId;
        listMagazinProduitByMagazinId = magazinProduitRepository.findByMagazin_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listMagazinProduitByMagazinId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!magazinRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        magazinRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
