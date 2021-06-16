package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.enumeration.Mouvement;
import com.bollore.stockbolloreback.enumeration.UserRoles;
import com.bollore.stockbolloreback.payload.EnumFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/common")
@Api(value = "Common controller",
        description = "Ressources API pour gérer les valeurs par défauts")
@CrossOrigin("*")
public class CommonController {

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des mouvements pour une demande concernant un produit")
    @GetMapping("/list-mouvement")
    public ResponseEntity<List<EnumFormat>> getMouvementList(){
        List<EnumFormat> enumFormatList = new ArrayList<>();
        enumFormatList.clear();

        enumFormatList.add(new EnumFormat(Mouvement.ENTRER.toString(), "Mouvement en entré"));
        enumFormatList.add(new EnumFormat(Mouvement.SORTIE.toString(), "Mouvement en sortie"));

        return ResponseEntity.status(HttpStatus.OK).body(enumFormatList);
    }

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des roles du systeme")
    @GetMapping("/list-roles")
    public ResponseEntity<List<EnumFormat>> getRolesList(){

        List<EnumFormat> enumFormatList2 = new ArrayList<>();
        enumFormatList2.clear();

        enumFormatList2.add(new EnumFormat(UserRoles.ROLE_ADMIN.toString(), "Administrateur"));
        enumFormatList2.add(new EnumFormat(UserRoles.ROLE_DEMANDEUR.toString(), "Demandeur"));
        enumFormatList2.add(new EnumFormat(UserRoles.ROLE_GESTIONNAIRE.toString(), "Gestionnaire"));
        enumFormatList2.add(new EnumFormat(UserRoles.ROLE_PERSONNE.toString(), "Personnel"));
        enumFormatList2.add(new EnumFormat(UserRoles.ROLE_VALIDATEUR.toString(), "Validateur"));

        return ResponseEntity.status(HttpStatus.OK).body(enumFormatList2);
    }

}
