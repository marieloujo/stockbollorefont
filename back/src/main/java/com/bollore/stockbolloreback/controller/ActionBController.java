package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.ActionB;
import com.bollore.stockbolloreback.models.ProfilAction;
import com.bollore.stockbolloreback.repository.ActionRepository;
import com.bollore.stockbolloreback.repository.ProfilActionRepository;
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
@RequestMapping("/action")
@Api(value = "Action controller",
        description = "Ressources API pour gérer les actions des personnes à bollore")
@CrossOrigin("*")
@Transactional
public class ActionBController {

    @Autowired
    private ActionRepository actionBRepository;

    @Autowired
    private ProfilActionRepository profilActionRepository;

    private static final String ENTITY_NAME = "stockBollore_Action";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des actions")
    @GetMapping(value = "/list")
    public ResponseEntity<List<ActionB>> getAllActionB(){
        return ResponseEntity.status(HttpStatus.OK).body(actionBRepository.findAllByOrderByCreatedDateDesc());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir une action par son id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<ActionB> getActionBById(@PathVariable("id") Long id){

        Optional<ActionB> act = actionBRepository.findById(id);

        ActionB actionB = act.get();

        return ResponseEntity.status(HttpStatus.OK).body(actionB);
    }

    @ApiOperation(value = "cette ressource permet d'ajouter une action")
    @PostMapping(value = "/creer-action")
    public ResponseEntity<ActionB> createActionB(@Valid @RequestBody ActionB actionB) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (actionB.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ActionB newActionB = actionBRepository.save(actionB);

        return ResponseEntity.created(new URI("/action/creer-action"+ newActionB.getId())).body(newActionB);
    }

    @ApiOperation(value = "cette ressource permet de modifier une action")
    @PutMapping(value = "/modifier-action")
    public ResponseEntity<ActionB> updateActionB(@RequestBody ActionB actionB){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (actionB.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!actionBRepository.existsById(actionB.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ActionB actionBToUpdate = actionBRepository.saveAndFlush(actionB);

        return ResponseEntity.ok().body(actionBToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer une action")
    @DeleteMapping(value = "/supprimer-action/{id}")
    public ResponseEntity<Void> deleteActionB(@PathVariable("id") Long id){
        List<ProfilAction> listProfilActionByActionBId;
        listProfilActionByActionBId = profilActionRepository.findByActionB_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listProfilActionByActionBId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!actionBRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        actionBRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }


}
