package com.bollore.stockbolloreback.controller;

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

@RestController
@RequestMapping("/profil-action")
@Api(value = "ProfilAction controller",
        description = "Ressources API pour gérer les profils lié aux actions à bollore")
@CrossOrigin("*")
@Transactional
public class ProfilActionController {
}
