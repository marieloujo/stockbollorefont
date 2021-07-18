package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.Personne;
import com.bollore.stockbolloreback.models.ServiceB;
import com.bollore.stockbolloreback.repository.PersonneRepository;
import com.bollore.stockbolloreback.repository.ServiceRepository;
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
@RequestMapping("/service")
@Api(value = "Service controller",
        description = "Ressources API pour gérer les services de bollore")
@CrossOrigin("*")
@Transactional
public class ServiceBController {

    @Autowired
    private ServiceRepository serviceBRepository;

    @Autowired
    private PersonneRepository personneRepository;

    private static final String ENTITY_NAME = "stockBollore_Service";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des services")
    @GetMapping(value = "/list")
    public ResponseEntity<List<ServiceB>> getAllServiceB(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceBRepository.findAllByOrderByCreatedDateDesc());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir un service par son id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<ServiceB> getServiceBById(@PathVariable("id") Long id){

        Optional<ServiceB> srv = serviceBRepository.findById(id);

        ServiceB serviceB = srv.get();

        return ResponseEntity.status(HttpStatus.OK).body(serviceB);
    }

    @ApiOperation(value = "cette ressource permet d'ajouter un service")
    @PostMapping(value = "/creer-service")
    public ResponseEntity<ServiceB> createServiceB(@Valid @RequestBody ServiceB serviceB) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer une service avec un id existant
        if (serviceB.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ServiceB newServiceB = serviceBRepository.save(serviceB);

        return ResponseEntity.created(new URI("/service/creer-service"+ newServiceB.getId())).body(newServiceB);
    }

    @ApiOperation(value = "cette ressource permet de modifier un service")
    @PutMapping(value = "/modifier-service")
    public ResponseEntity<ServiceB> updateServiceB(@RequestBody ServiceB serviceB){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (serviceB.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!serviceBRepository.existsById(serviceB.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ServiceB serviceBToUpdate = serviceBRepository.saveAndFlush(serviceB);

        return ResponseEntity.ok().body(serviceBToUpdate);
    }

    @ApiOperation(value = "cette ressource permet de supprimer un service")
    @DeleteMapping(value = "/supprimer-service/{id}")
    public ResponseEntity<Void> deleteServiceB(@PathVariable("id") Long id){
        List<Personne> listPersonneByServiceBId;
        listPersonneByServiceBId = personneRepository.findByServiceB_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listPersonneByServiceBId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!serviceBRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        serviceBRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }





}
