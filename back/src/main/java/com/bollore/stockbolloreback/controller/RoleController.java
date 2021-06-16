package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.models.Role;
import com.bollore.stockbolloreback.repository.RoleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/roles")
@Api(value = "Role controller",
        description = "Ressources API pour gérer les roles")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    private static final String ENTITY_NAME = "stockBollore_Role";

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des roles utilisateur")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Role>> getAllRole(){
        return ResponseEntity.status(HttpStatus.OK).body(roleRepository.findAll());
    }

}
