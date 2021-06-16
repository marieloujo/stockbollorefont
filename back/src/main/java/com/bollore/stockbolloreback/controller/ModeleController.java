package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.Modele;
import com.bollore.stockbolloreback.models.Produit;
import com.bollore.stockbolloreback.repository.ModeleRepository;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modele")
@Api(value = "Modele controller",
        description = "Ressources API pour gérer les modeles de stokage de bollore")
@CrossOrigin("*")
@Transactional
public class ModeleController {

    @Autowired
    private ModeleRepository modeleRepository;

    @Autowired
    private ProduitRepository produitRepository;

    private static final String ENTITY_NAME = "stockBollore_Modele";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des modeles")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Modele>> getAllModeles(){
        return ResponseEntity.status(HttpStatus.OK).body(modeleRepository.findAll());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir un modele par son id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Modele> getModeleById(@PathVariable("id") Long id){

        Optional<Modele> mdl = modeleRepository.findById(id);

        Modele modele = mdl.get();

        return ResponseEntity.status(HttpStatus.OK).body(modele);
    }

    @ApiOperation(value = "cette ressource permet d'ajouter un modele")
    @PostMapping(value = "/creer-modele")
    public ResponseEntity<Modele> createModele(@Valid @RequestBody Modele modele) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un modele avec un id existant
        if (modele.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Modele newModele = modeleRepository.save(modele);

        return ResponseEntity.created(new URI("/modele/creer-modele"+ newModele.getId())).body(newModele);
    }

    @ApiOperation(value = "cette ressource permet de modifier un modele")
    @PutMapping(value = "/modifier-modele")
    public ResponseEntity<Modele> updateModele(@RequestBody Modele modele){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un modele qui n'existe pas
        if (modele.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un modele qui n'existe pas
        if (!modeleRepository.existsById(modele.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Modele modeleToUpdate = modeleRepository.saveAndFlush(modele);

        return ResponseEntity.ok().body(modeleToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer un modele")
    @DeleteMapping(value = "/supprimer-modele/{id}")
    public ResponseEntity<Void> deleteModele(@PathVariable("id") Long id){
        List<Produit> listProduitByModeleId;
        listProduitByModeleId = produitRepository.findByModele_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listProduitByModeleId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!modeleRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        modeleRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }


}
