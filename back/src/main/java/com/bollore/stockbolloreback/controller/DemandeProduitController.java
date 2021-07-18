package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.enumeration.EnumDemandeStatus;
import com.bollore.stockbolloreback.enumeration.EnumDemandeType;
import com.bollore.stockbolloreback.enumeration.EnumProduitEtat;
import com.bollore.stockbolloreback.enumeration.EnumProduitStatus;
import com.bollore.stockbolloreback.models.Demande;
import com.bollore.stockbolloreback.models.DemandeProduit;
import com.bollore.stockbolloreback.models.DemandeRetourForm;
import com.bollore.stockbolloreback.models.Produit;
import com.bollore.stockbolloreback.repository.DemandeProduitRepository;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * The type Demande produit controller.
 */
@RestController
@RequestMapping("/demande-produit")
@Api(value = "DemandeProduit controller",
        description = "Ressources API pour gérer les différentes demande concernant les produits à bollore")
@CrossOrigin("*")
@Transactional
public class DemandeProduitController {

    @Autowired
    private DemandeProduitRepository demandeProduitRepository;

    @Autowired
    private ProduitRepository produitRepository;

    private static final String ENTITY_NAME = "stockBollore_DemandeProduit";

    /**
     * Get all demande produit response entity.
     *
     * @return the response entity
     */
    @ApiOperation(value = "cette ressource permet d'obtenir la liste des demandes produit")
    @GetMapping(value = "/list")
    public ResponseEntity<List<DemandeProduit>> getAllDemandeProduit(){
        return ResponseEntity.status(HttpStatus.OK).body(demandeProduitRepository.findAllByOrderByCreatedDateDesc());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir la liste des demandes retour produit")
    @GetMapping(value = "/list/for-retour")
    public ResponseEntity<List<DemandeProduit>> getListRetourDemandeProduit(){
        List<EnumDemandeStatus> list = Arrays.asList(
                EnumDemandeStatus.LIVREE,
                EnumDemandeStatus.RETOUR_REJETEE,
                EnumDemandeStatus.RETOUR_REJETEE
        );
        List<EnumProduitStatus> enumProduitStatusList = Arrays.asList(
                EnumProduitStatus.EN_REPARATION
        );
        List<DemandeProduit> result = demandeProduitRepository.findByStatusInOrProduitStatusInOrderByCreatedDateDesc(list, enumProduitStatusList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Gets demande produit created by.
     *
     * @param id the id
     * @return the demande produit created by
     */
    @ApiOperation(value = "cette ressource permet d'obtenir le created By de demandeProduit")
    @GetMapping(value = "/demande-produit-createdBy/{id}")
    public ResponseEntity<List<String>> getDemandeProduitCreatedBy(@PathVariable("id") Long id){

        List<String> demandeurEtDate = new ArrayList<>();

        Optional<DemandeProduit> dmdProd = demandeProduitRepository.findById(id);

        DemandeProduit demandeProduit = dmdProd.get();

        demandeurEtDate.add(demandeProduit.getCreatedBy());
        demandeurEtDate.add(demandeProduit.getCreatedDate().toString());

        return ResponseEntity.status(HttpStatus.OK).body(demandeurEtDate);
    }

    /**
     * Get all demande produit des by create date response entity.
     *
     * @return the response entity
     */
    @ApiOperation(value = "cette ressource permet d'obtenir la liste des demandes produit par ordre décoissant")
    @GetMapping(value = "/list/desc-create-date")
    public ResponseEntity<List<DemandeProduit>> getAllDemandeProduitDesByCreateDate(){
        //return ResponseEntity.status(HttpStatus.OK).body(demandeProduitRepository.findTop10ByOrderByCreatedDateDesc());
        List<EnumDemandeStatus> demandeStatusList = Arrays.asList(
                EnumDemandeStatus.REJETEE,
                EnumDemandeStatus.LIVREE,
                EnumDemandeStatus.RETOUR_REJETEE,
                EnumDemandeStatus.RETOUR_VALIDEE
        );
        List<DemandeProduit> result = demandeProduitRepository.findByStatusNotInOrderByCreatedDateDesc(demandeStatusList);
       // return ResponseEntity.status(HttpStatus.OK).body(demandeProduitRepository.findAllByLivrerIsFalseOrderByCreatedDateDesc());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Create demande produit response entity.
     *
     * @param demandeProduit the demande produit
     * @return the response entity
     * @throws URISyntaxException the uri syntax exception
     */
    @ApiOperation(value = "cette ressource permet d'ajouter des produits a une demande")
    @PostMapping(value = "/creer-demande-produit")
    public ResponseEntity<DemandeProduit> createDemandeProduit(@Valid @RequestBody DemandeProduit demandeProduit) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (demandeProduit.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // update status produit
        Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
          if(produit != null) {

              // SORTIE
              if(EnumDemandeType.SORTIE.equals(demandeProduit.getDemande().getTypeDemande())){
                  produit.setStatus(EnumProduitStatus.EN_ATTENTE_VALIDATION);
              }
              // SORTIE_REPARATION
              if(EnumDemandeType.SORTIE_REPARATION.equals(demandeProduit.getDemande().getTypeDemande())){
                  produit.setStatus(EnumProduitStatus.EN_ATTENTE_ENVOIE_REPARATION);
              }
              // SORTIE_REBUT
              if(EnumDemandeType.SORTIE_REBUT.equals(demandeProduit.getDemande().getTypeDemande())){
                  produit.setStatus(EnumProduitStatus.EN_ATTENTE_DE_MISE_AU_REBUT);
              }
              produitRepository.save(produit);
          }
        demandeProduit.setStatus(EnumDemandeStatus.EN_ATTENTE);
        DemandeProduit newDemandeProduit = demandeProduitRepository.save(demandeProduit);

        return ResponseEntity.created(new URI("/demande-produit/creer-demande-produit"+ newDemandeProduit.getId())).body(newDemandeProduit);
    }

    /**
     * Create demande produit rep response entity.
     *
     * @param demandeProduit the demande produit
     * @return the response entity
     * @throws URISyntaxException the uri syntax exception
     */
    @ApiOperation(value = "cette ressource permet d'ajouter des produits a une demande")
    @PostMapping(value = "/creer-demande-produit-rep")
    public ResponseEntity<DemandeProduit> createDemandeProduitRep(@Valid @RequestBody DemandeProduit demandeProduit) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (demandeProduit.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // update status produit
        Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
          if(produit != null) {
              produit.setStatus(EnumProduitStatus.EN_ATTENTE_ENVOIE_REPARATION);
              produitRepository.save(produit);
          }
        DemandeProduit newDemandeProduit = demandeProduitRepository.save(demandeProduit);

        return ResponseEntity.created(new URI("/demande-produit/creer-demande-produit-rep"+ newDemandeProduit.getId())).body(newDemandeProduit);
    }


    /**
     * Update demande produit response entity.
     *
     * @param demandeProduit the demande produit
     * @return the response entity
     */
    @ApiOperation(value = "cette ressource permet de modifier une demande ayant rapport a un produit")
    @PutMapping(value = "/modifier-demande-produit")
    public ResponseEntity<DemandeProduit> updateDemandeProduit(@RequestBody DemandeProduit demandeProduit){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (demandeProduit.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!demandeProduitRepository.existsById(demandeProduit.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DemandeProduit demandeProduitToUpdate = demandeProduitRepository.saveAndFlush(demandeProduit);

        return ResponseEntity.ok().body(demandeProduitToUpdate);

    }

    /**
     * Gets all demande produit between create date.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the all demande produit between create date
     */
    @ApiOperation(value = "cette ressource permet d'obtenir la liste des demandes produit contenu entre deux dates")
    @GetMapping(value = "/list/between-created-date")
    public ResponseEntity<List<DemandeProduit>> getAllDemandeProduitBetweenCreateDate(@RequestParam LocalDate startDate,@RequestParam("data02") LocalDate endDate){

        Instant instant01 = startDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant instant02 = endDate != null ? endDate.atTime(LocalTime.of(23, 59, 59)).toInstant(ZoneOffset.UTC)
                : startDate.atTime(LocalTime.of(23, 59, 59)).toInstant(ZoneOffset.UTC);

        return ResponseEntity.status(HttpStatus.OK).body(demandeProduitRepository.findAllByCreatedByIsBetween(instant01, instant02));
    }


    /**
     * Gets all demande produit between demande date.
     *
     * @param firstDate the start date
     * @param lastDate   the end date
     * @return the all demande produit between demande date
     */
    @ApiOperation(value = "cette ressource permet d'obtenir la liste des demandes produit entre deux date de demandes")
    @GetMapping(value = "/list/between-demande-date/{firstDate}/{lastDate}")
    public ResponseEntity<List<DemandeProduit>> getAllDemandeProduitBetweenDemandeDate(@RequestParam LocalDate firstDate,@RequestParam LocalDate lastDate){

        Instant instant1 = firstDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant instant2 = lastDate != null ? lastDate.atTime(LocalTime.of(23, 59, 59)).toInstant(ZoneOffset.UTC)
                : firstDate.atTime(LocalTime.of(23, 59, 59)).toInstant(ZoneOffset.UTC);

        return ResponseEntity.status(HttpStatus.OK).body(demandeProduitRepository.findAllByDemande_DateHeureIsBetween(instant1, instant2));
    }

    /**
     * Rejeter demande response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation(value = "cette ressource permet de rejeter une demande")
    @PostMapping(value = "/rejeter/{id}")
    public ResponseEntity<DemandeProduit> rejeterDemande(@PathVariable("id") Long id){
        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        DemandeProduit demandeProduit = demandeProduitRepository.findById(id).orElse(null);
        if (demandeProduit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // demande status
        demandeProduit.setStatus(EnumDemandeStatus.REJETEE);
        demandeProduit.setDateRejet(new Date());
        demandeProduit = demandeProduitRepository.save(demandeProduit);
        Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
            if(produit != null) {
                produit.setStatus(EnumProduitStatus.EN_STOCK);
                produitRepository.save(produit);
        }
        return new ResponseEntity<DemandeProduit>(demandeProduit, HttpStatus.OK);
    }


    /**
     * Livrer demande response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation(value = "cette ressource permet de livrer une demande")
    @PostMapping(value = "/livrer/{id}")
    public ResponseEntity<DemandeProduit> livrerDemande(@PathVariable("id") Long id){
        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        DemandeProduit demandeProduit = demandeProduitRepository.findById(id).orElse(null);
        if (demandeProduit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // demande status
        demandeProduit.setStatus(EnumDemandeStatus.LIVREE);
        demandeProduit.setDateLivraison(new Date());
        demandeProduit = demandeProduitRepository.save(demandeProduit);
        Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
        if(produit != null) {
            // uniquement pour les demande de sortie
            if(EnumDemandeType.SORTIE.equals(demandeProduit.getDemande().getTypeDemande())){
                produit.setEtatActuel(EnumProduitEtat.ETAT);
                produit.setStatus(EnumProduitStatus.EN_UTILISAION);
                produitRepository.save(produit);
            }

            // SORTIE_REPARATION
            if(EnumDemandeType.SORTIE_REPARATION.equals(demandeProduit.getDemande().getTypeDemande())){
                produit.setStatus(EnumProduitStatus.EN_REPARATION);
            }
            // SORTIE_REBUT
            if(EnumDemandeType.SORTIE_REBUT.equals(demandeProduit.getDemande().getTypeDemande())){
                produit.setStatus(EnumProduitStatus.MISE_AU_REBUT);
            }
        }
        return new ResponseEntity<DemandeProduit>(demandeProduit, HttpStatus.OK);
    }

    /**
     * Valider demande response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation(value = "cette ressource permet de valider une demande")
    @PostMapping(value = "/valider/{id}")
    public ResponseEntity<DemandeProduit> validerDemande(@PathVariable("id") Long id){
        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        DemandeProduit demandeProduit = demandeProduitRepository.findById(id).orElse(null);
        if (demandeProduit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // demande status
        demandeProduit.setStatus(EnumDemandeStatus.VALIDEE);
        demandeProduit.setDateValidation(new Date());
        demandeProduit = demandeProduitRepository.save(demandeProduit);
        Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
        if (produit != null) {

            // SORTIE
            if(EnumDemandeType.SORTIE.equals(demandeProduit.getDemande().getTypeDemande())){
                produit.setStatus(EnumProduitStatus.EN_ATTENTE_SORTIE_MAG_STA);
            }
            // SORTIE_REPARATION
            if(EnumDemandeType.SORTIE_REPARATION.equals(demandeProduit.getDemande().getTypeDemande())){
                produit.setStatus(EnumProduitStatus.EN_ATTENTE_SORTIE_MAG_REP);
            }
            // SORTIE_REBUT
            if(EnumDemandeType.SORTIE_REBUT.equals(demandeProduit.getDemande().getTypeDemande())){
                produit.setStatus(EnumProduitStatus.EN_ATTENTE_SORTIE_MAG_REB);
            }
            produitRepository.save(produit);
        }
        return new ResponseEntity<DemandeProduit>(demandeProduit, HttpStatus.OK);
    }


    /**
    * mise à disposition du demandeur demande response entity.
    *
    * @param id the id
    * @return the response entity
    */
   @ApiOperation(value = "cette ressource : confirmation sortie de magasin")
   @PostMapping(value = "/mettreDisposition/{id}")
   public ResponseEntity<DemandeProduit> mettreDisposition(@PathVariable("id") Long id){
       if (id == null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       DemandeProduit demandeProduit = demandeProduitRepository.findById(id).orElse(null);
       if (demandeProduit == null) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       // demande status
       demandeProduit.setStatus(EnumDemandeStatus.VALIDEE);
       demandeProduit.setDateValidation(new Date());
       demandeProduit = demandeProduitRepository.save(demandeProduit);
       Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
       if (produit != null) {

           // SORTIE
           if(EnumDemandeType.SORTIE.equals(demandeProduit.getDemande().getTypeDemande())){
               produit.setStatus(EnumProduitStatus.EN_ATTENTE_RECEPTION_STA);
           }
           // SORTIE_REPARATION
           if(EnumDemandeType.SORTIE_REPARATION.equals(demandeProduit.getDemande().getTypeDemande())){
               produit.setStatus(EnumProduitStatus.EN_ATTENTE_RECEPTION_REP);
           }
           // SORTIE_REBUT
           if(EnumDemandeType.SORTIE_REBUT.equals(demandeProduit.getDemande().getTypeDemande())){
               produit.setStatus(EnumProduitStatus.EN_ATTENTE_DE_MISE_AU_REBUT);
           }
           produitRepository.save(produit);
       }
       return new ResponseEntity<DemandeProduit>(demandeProduit, HttpStatus.OK);

   }



   /**
    * accuser de reception demande response entity.
    *
    * @param id the id
    * @return the response entity
    */
   @ApiOperation(value = "cette ressource : confirmation reception par demandeur")
   @PostMapping(value = "/accuserReceptionDemande/{id}")
   public ResponseEntity<DemandeProduit> accuserReceptionDemande(@PathVariable("id") Long id){
       if (id == null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       DemandeProduit demandeProduit = demandeProduitRepository.findById(id).orElse(null);
       if (demandeProduit == null) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       // demande status
       demandeProduit.setStatus(EnumDemandeStatus.VALIDEE);
       demandeProduit.setDateValidation(new Date());
       demandeProduit = demandeProduitRepository.save(demandeProduit);
       Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
       if (produit != null) {

           // SORTIE
           if(EnumDemandeType.SORTIE.equals(demandeProduit.getDemande().getTypeDemande())){
               produit.setStatus(EnumProduitStatus.EN_ATTENTE_LIVRAISON);
           }
           // SORTIE_REPARATION
           if(EnumDemandeType.SORTIE_REPARATION.equals(demandeProduit.getDemande().getTypeDemande())){
               produit.setStatus(EnumProduitStatus.EN_ATTENTE_LIVRAISON_REP);
           }
           // SORTIE_REBUT
           if(EnumDemandeType.SORTIE_REBUT.equals(demandeProduit.getDemande().getTypeDemande())){
               produit.setStatus(EnumProduitStatus.EN_ATTENTE_LIVRAISON_REB);
           }
           produitRepository.save(produit);
       }
       return new ResponseEntity<DemandeProduit>(demandeProduit, HttpStatus.OK);
   }














    /**
     * Retour create demande response entity.
     *
     * @param demandeRetourForm the demande retour form
     * @return the response entity
     */
    @ApiOperation(value = "cette ressource permet de créer une demande retour")
    @PostMapping(value = "/retour/create")
    public ResponseEntity<DemandeProduit> retourCreateDemande(@RequestBody DemandeRetourForm demandeRetourForm){
        if (demandeRetourForm.getDemandeProduitId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        DemandeProduit demandeProduit = demandeProduitRepository.findById(demandeRetourForm.getDemandeProduitId()).orElse(null);
        if (demandeProduit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
        if (produit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // validation gestionnaire
        if(demandeRetourForm.getCanValidate()) {
            demandeProduit.setStatus(EnumDemandeStatus.RETOUR_EN_ATTENNTE);
            demandeProduit.setDateDemandeRetour(new Date());
            demandeProduit.setEtatProduitRetour(demandeRetourForm.getEtatProduitRetour());
            demandeProduit = demandeProduitRepository.save(demandeProduit);
            return retourValiderDemande(demandeRetourForm);
        } else
            // cas de creation 1
            if(demandeProduit.getDateDemandeRetour1() == null) {
                demandeProduit.setDateDemandeRetour1(new Date());
                demandeProduit.setEtatProduitRetour1(demandeRetourForm.getEtatProduitRetour());
            } else
                // cas de creation 2
                if(demandeProduit.getDateDemandeRetour2() == null) {
                    demandeProduit.setDateDemandeRetour2(new Date());
                    demandeProduit.setEtatProduitRetour2(demandeRetourForm.getEtatProduitRetour());
                } else
                    // cas de creation 3
                    if(demandeProduit.getDateDemandeRetour3() == null) {
                        demandeProduit.setDateDemandeRetour3(new Date());
                        demandeProduit.setEtatProduitRetour3(demandeRetourForm.getEtatProduitRetour());
                    }
        // demande status
        demandeProduit.setEtatProduitRetour(demandeRetourForm.getEtatProduitRetour());
        demandeProduit.setStatus(EnumDemandeStatus.RETOUR_EN_ATTENNTE);
        demandeProduit = demandeProduitRepository.save(demandeProduit);
        return new ResponseEntity<DemandeProduit>(demandeProduit, HttpStatus.OK);
    }

    /**
     * Retour valider demande response entity.
     *
     * @param demandeRetourForm the demande retour form
     * @return the response entity
     */
    @ApiOperation(value = "cette ressource permet de valider une demande de retour")
    @PostMapping(value = "/retour/valider")
    public ResponseEntity<DemandeProduit> retourValiderDemande(@RequestBody DemandeRetourForm demandeRetourForm){
        if (demandeRetourForm.getDemandeProduitId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        DemandeProduit demandeProduit = demandeProduitRepository.findById(demandeRetourForm.getDemandeProduitId()).orElse(null);
        if (demandeProduit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
        if (produit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // validation gestionnaire
        if(demandeRetourForm.getCanValidate()) {
            demandeProduit.setDateValidationRetour(new Date());
            produit.setEtatActuel(demandeRetourForm.getEtatProduitRetour());
        } else
        // cas de validation 1
        if(demandeProduit.getDateRejetRetour1() == null && demandeProduit.getDateValidationRetour1() == null) {
            demandeProduit.setDateRejetRetour1(new Date());
            produit.setEtatActuel(demandeProduit.getEtatProduitRetour1());
        } else
            // cas de validation 2
            if(demandeProduit.getDateRejetRetour2() == null && demandeProduit.getDateValidationRetour2() == null) {
                demandeProduit.setDateRejetRetour2(new Date());
                produit.setEtatActuel(demandeProduit.getEtatProduitRetour2());
            } else
                // cas de validation 3
                if(demandeProduit.getDateRejetRetour3() == null && demandeProduit.getDateValidationRetour3() == null) {
                    demandeProduit.setDateRejetRetour3(new Date());
                    produit.setEtatActuel(demandeProduit.getEtatProduitRetour3());
                }
        // demande status
        demandeProduit.setStatus(EnumDemandeStatus.RETOUR_VALIDEE);
        demandeProduit = demandeProduitRepository.save(demandeProduit);
        // save product
        produit.setStatus(EnumProduitStatus.EN_STOCK);
        produitRepository.save(produit);
        return new ResponseEntity<DemandeProduit>(demandeProduit, HttpStatus.OK);
    }

    /**
     * Retour rejeter demande response entity.
     *
     * @param demandeRetourForm the demande retour form
     * @return the response entity
     */
    @ApiOperation(value = "cette ressource permet de rejeter une demande de retour")
    @PostMapping(value = "/retour/rejeter")
    public ResponseEntity<DemandeProduit> retourRejeterDemande(@RequestBody DemandeRetourForm demandeRetourForm){
        if (demandeRetourForm.getDemandeProduitId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        DemandeProduit demandeProduit = demandeProduitRepository.findById(demandeRetourForm.getDemandeProduitId()).orElse(null);
        if (demandeProduit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Produit produit = produitRepository.findById(demandeProduit.getProduit().getId()).orElse(null);
        if (produit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // cas de rejet 1
        if(demandeProduit.getDateRejetRetour1() == null) {
            demandeProduit.setDateRejetRetour1(new Date());
        } else
        // cas de rejet 2
        if(demandeProduit.getDateRejetRetour2() == null) {
            demandeProduit.setDateRejetRetour2(new Date());
        } else
        // cas de rejet 3
        if(demandeProduit.getDateRejetRetour3() == null) {
            demandeProduit.setDateRejetRetour3(new Date());
        }
        // demande status
        demandeProduit.setStatus(EnumDemandeStatus.RETOUR_REJETEE);
        demandeProduit = demandeProduitRepository.save(demandeProduit);
        // todo:
        if (produit != null) {

          //  produitRepository.save(produit);
        }
        return new ResponseEntity<DemandeProduit>(demandeProduit, HttpStatus.OK);
    }

}
