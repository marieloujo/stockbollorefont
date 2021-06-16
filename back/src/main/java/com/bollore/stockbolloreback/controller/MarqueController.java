package com.bollore.stockbolloreback.controller;


import com.bollore.stockbolloreback.models.Marque;
import com.bollore.stockbolloreback.models.Produit;
import com.bollore.stockbolloreback.repository.MarqueRepository;
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
@RequestMapping("/marque")
@Api(value = "Marque controller",
        description = "Ressources API pour gérer les marques de stokage de bollore")
@CrossOrigin("*")
@Transactional
public class MarqueController {

    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private ProduitRepository produitRepository;

    private static final String ENTITY_NAME = "stockBollore_Marque";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des marques")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Marque>> getAllMarques(){
        return ResponseEntity.status(HttpStatus.OK).body(marqueRepository.findAll());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir une marque par son id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Marque> getMarqueById(@PathVariable("id") Long id){

        Optional<Marque> mrq = marqueRepository.findById(id);

        Marque marque = mrq.get();

        return ResponseEntity.status(HttpStatus.OK).body(marque);
    }

    @ApiOperation(value = "cette ressource permet d'ajouter un marque")
    @PostMapping(value = "/creer-marque")
    public ResponseEntity<Marque> createMagazin(@Valid @RequestBody Marque marque) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un marque avec un id existant
        if (marque.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Marque newMarque = marqueRepository.save(marque);

        return ResponseEntity.created(new URI("/marque/creer-marque"+ newMarque.getId())).body(newMarque);
    }

    @ApiOperation(value = "cette ressource permet de modifier un marque")
    @PutMapping(value = "/modifier-marque")
    public ResponseEntity<Marque> updateMarque(@RequestBody Marque marque){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un marque qui n'existe pas
        if (marque.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!marqueRepository.existsById(marque.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Marque marqueToUpdate = marqueRepository.saveAndFlush(marque);

        return ResponseEntity.ok().body(marqueToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer un marque")
    @DeleteMapping(value = "/supprimer-marque/{id}")
    public ResponseEntity<Void> deleteMarque(@PathVariable("id") Long id){
        List<Produit> listProduitByMarqueId;
        listProduitByMarqueId = produitRepository.findByMarque_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listProduitByMarqueId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!marqueRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        marqueRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }


}
