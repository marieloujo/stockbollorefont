package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.Personne;
import com.bollore.stockbolloreback.repository.PersonneRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personne")
@Api(value = "Personne controller",
        description = "Ressources API pour gérer le personnel de bollore")
@CrossOrigin("*")
@Transactional
public class PersonneController {

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private PasswordEncoder encoder;

    private static final String ENTITY_NAME = "stockBollore_Personne";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des personnes")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Personne>> getAllPersonne(){
        return ResponseEntity.status(HttpStatus.OK).body(personneRepository.findAll());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir une personne par son id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable("id") Long id){

        Optional<Personne> prs = personneRepository.findById(id);

        Personne personne = prs.get();

        return ResponseEntity.status(HttpStatus.OK).body(personne);
    }

    @ApiOperation(value = "cette ressource permet d'obtenir une personne par son username")
    @GetMapping(value = "/get-by-username01/{username1}")
    public ResponseEntity<Personne> getPersonneByUsername01(@PathVariable("username1") String username1){

        Optional<Personne> prs = personneRepository.findByUsername(username1);

        Personne personne = prs.get();

        return ResponseEntity.status(HttpStatus.OK).body(personne);
    }

    @ApiOperation(value = "cette ressource permet d'obtenir une personne par son username")
    @GetMapping(value = "/get-by-username02/{username1}/{username2}")
    public ResponseEntity<List<Personne>> getPersonneByUsername02(@PathVariable("username1") String username1, @PathVariable(value = "username2") Optional<String> username2){

        List<Personne> personneListByUsername = new ArrayList<>();
        personneListByUsername.add(personneRepository.findByUsername(username1).get());

        if (username2.isPresent())
            personneListByUsername.add(personneRepository.findByUsername(username2.get()).get());

        Optional<Personne> prs = personneRepository.findByUsername(username1);

        Personne personne = prs.get();

        return ResponseEntity.status(HttpStatus.OK).body(personneListByUsername);
    }

    @ApiOperation(value = "cette ressource permet d'ajouter une personne")
    @PostMapping(value = "/creer-personne")
    public ResponseEntity<Personne> createPersonne(@Valid @RequestBody Personne personne) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (personne.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        personne.setPassword(encoder.encode(personne.getPassword()));
        Personne newPersonne = personneRepository.save(personne);

        return ResponseEntity.created(new URI("/personne/creer-personne"+ newPersonne.getId())).body(newPersonne);
    }

    @ApiOperation(value = "cette ressource permet de modifier les informations d'une personne")
    @PutMapping(value = "/modifier-personne")
    public ResponseEntity<Personne> updatePeronne(@RequestBody Personne personne){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (personne.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!personneRepository.existsById(personne.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }



        if (personne.getPassword().isEmpty()) {
            personne.setPassword(personneRepository.getOne(personne.getId()).getPassword());
        } else {
            personne.setPassword(encoder.encode(personne.getPassword()));
        }

        Personne personneToUpdate = personneRepository.saveAndFlush(personne);

        return ResponseEntity.ok().body(personneToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer une personne")
    @DeleteMapping(value = "/supprimer-personne/{id}")
    public ResponseEntity<Void> deletePeronne(@PathVariable("id") Long id){
        /*List<Produit> listProduitByPersonneId;
        listProduitByPersonneId = produitRepository.findByPersonne_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listProduitByPersonneId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!personneRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        personneRepository.deleteById(id);*/

        return ResponseEntity.noContent().build();

    }



}
