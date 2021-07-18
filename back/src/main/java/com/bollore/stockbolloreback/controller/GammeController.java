package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.Gamme;
import com.bollore.stockbolloreback.models.Produit;
import com.bollore.stockbolloreback.repository.GammeRepository;
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
@RequestMapping("/gamme")
@Api(value = "Gamme controller",
        description = "Ressources API pour gérer les gammes de produit pour stokage de bollore")
@CrossOrigin("*")
@Transactional
public class GammeController {

    @Autowired
    private GammeRepository gammeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    private static final String ENTITY_NAME = "stockBollore_Gamme";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des gammes")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Gamme>> getAllGammes(){
        return ResponseEntity.status(HttpStatus.OK).body(gammeRepository.findAllByOrderByCreatedDateDesc());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir une gamme par son id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Gamme> getGammeById(@PathVariable("id") Long id){

        Optional<Gamme> gm = gammeRepository.findById(id);

        Gamme gamme = gm.get();

        return ResponseEntity.status(HttpStatus.OK).body(gamme);
    }

    @ApiOperation(value = "cette ressource permet d'ajouter un gamme")
    @PostMapping(value = "/creer-gamme")
    public ResponseEntity<Gamme> createGamme(@Valid @RequestBody Gamme gamme) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un gamme avec un id existant
        if (gamme.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Gamme newGamme = gammeRepository.save(gamme);

        return ResponseEntity.created(new URI("/gamme/creer-gamme"+ newGamme.getId())).body(newGamme);
    }

    @ApiOperation(value = "cette ressource permet de modifier une gamme")
    @PutMapping(value = "/modifier-gamme")
    public ResponseEntity<Gamme> updateGamme(@RequestBody Gamme gamme){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier une gamme qui n'existe pas
        if (gamme.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!gammeRepository.existsById(gamme.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Gamme gammeToUpdate = gammeRepository.saveAndFlush(gamme);

        return ResponseEntity.ok().body(gammeToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer un gamme")
    @DeleteMapping(value = "/supprimer-gamme/{id}")
    public ResponseEntity<Void> deleteGamme(@PathVariable("id") Long id){
        List<Produit> listProduitByGammeId;
        listProduitByGammeId = produitRepository.findByGamme_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listProduitByGammeId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!gammeRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        gammeRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }




}
