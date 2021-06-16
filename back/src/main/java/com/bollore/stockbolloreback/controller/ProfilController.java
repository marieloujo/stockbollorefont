package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.Personne;
import com.bollore.stockbolloreback.models.Profil;
import com.bollore.stockbolloreback.models.ProfilAction;
import com.bollore.stockbolloreback.models.Role;
import com.bollore.stockbolloreback.repository.PersonneRepository;
import com.bollore.stockbolloreback.repository.ProfilActionRepository;
import com.bollore.stockbolloreback.repository.ProfilRepository;
import com.bollore.stockbolloreback.repository.RoleRepository;
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
@RequestMapping("/profil")
@Api(value = "Profil controller",
        description = "Ressources API pour gérer les profils des utilisateurs de bollore")
@CrossOrigin("*")
@Transactional
public class ProfilController {

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private ProfilActionRepository profilActionRepository;

    private static final String ENTITY_NAME = "stockBollore_Profil";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des profils")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Role>> getAllProfil(){
        return ResponseEntity.status(HttpStatus.OK).body(roleRepository.findAll());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir un profil par son id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Profil> getProfilById(@PathVariable("id") Long id){

        Optional<Profil> prf = profilRepository.findById(id);

        Profil profil = prf.get();

        return ResponseEntity.status(HttpStatus.OK).body(profil);
    }

    @ApiOperation(value = "cette ressource permet d'ajouter un profil")
    @PostMapping(value = "/creer-profil")
    public ResponseEntity<Profil> createProfil(@Valid @RequestBody Profil profil) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (profil.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Profil newProfil = profilRepository.save(profil);

        return ResponseEntity.created(new URI("/profil/creer-profil"+ newProfil.getId())).body(newProfil);
    }

    @ApiOperation(value = "cette ressource permet de modifier un profil")
    @PutMapping(value = "/modifier-profil")
    public ResponseEntity<Profil> updateProfil(@RequestBody Profil profil){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (profil.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!profilRepository.existsById(profil.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Profil profilToUpdate = profilRepository.saveAndFlush(profil);

        return ResponseEntity.ok().body(profilToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer un profil")
    @DeleteMapping(value = "/supprimer-profil/{id}")
    public ResponseEntity<Void> deleteProfil(@PathVariable("id") Long id){
        List<Personne> listPersonneByProfilId;
        listPersonneByProfilId = personneRepository.findByProfil_Id(id);

        List<ProfilAction> listProfilActionByProfilId;
        listProfilActionByProfilId = profilActionRepository.findByProfil_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listPersonneByProfilId.isEmpty() || !listProfilActionByProfilId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!personneRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        profilRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }



}
