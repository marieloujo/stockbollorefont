package com.bollore.stockbolloreback.controller;

import com.bollore.stockbolloreback.enumeration.EnumDemandeStatus;
import com.bollore.stockbolloreback.enumeration.EnumProduitStatus;
import com.bollore.stockbolloreback.enumeration.EnumProduitEtat;
import com.bollore.stockbolloreback.models.Demande;
import com.bollore.stockbolloreback.models.DemandeProduit;
import com.bollore.stockbolloreback.models.Etat;
import com.bollore.stockbolloreback.models.Produit;
import com.bollore.stockbolloreback.repository.DemandeProduitRepository;
import com.bollore.stockbolloreback.repository.DemandeRepository;
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
import java.util.*;

@RestController
@RequestMapping("/demande")
@Api(value = "Demande controller",
        description = "Ressources API pour gérer les demandes de produits en stokage à bollore")
@CrossOrigin("*")
@Transactional
public class DemandeController {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private DemandeProduitRepository demandeProduitRepository;

    private static final String ENTITY_NAME = "stockBollore_Demande";

    @ApiOperation(value = "cette ressource permet d'obtenir liste des demandes")
    @GetMapping(value = "/list")
    public ResponseEntity<List<Demande>> getAllDemande(){
        return ResponseEntity.status(HttpStatus.OK).body(demandeRepository.findAll());
    }

    @ApiOperation(value = "cette ressource permet d'obtenir une demande par id")
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Demande> getDemandeById(@PathVariable("id") Long id){

        Optional<Demande> dmd = demandeRepository.findById(id);

        Demande demande = dmd.get();

        return ResponseEntity.status(HttpStatus.OK).body(demande);
    }

    @ApiOperation(value = "cette ressource permet de créer une demande")
    @PostMapping(value = "/creer-demande")
    public ResponseEntity<Demande> createDemande(@Valid @RequestBody Demande demande) throws URISyntaxException {
        //si id dans l'objet ==> BadRequest ..On ne peut pas creer un magazin avec un id existant
        if (demande.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        demande.setStatus(EnumDemandeStatus.EN_ATTENTE);
        Demande newDemande = demandeRepository.save(demande);

        return ResponseEntity.created(new URI("/demande/creer-demande"+ newDemande.getId())).body(newDemande);
    }

    @ApiOperation(value = "cette ressource permet de modifier une demande")
    @PutMapping(value = "/modifier-demande")
    public ResponseEntity<Demande> updateDemande(@RequestBody Demande demande){
        //si id pas dans l'objet ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (demande.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //si id dans l'objet mais inconnu dans la base de donnee ==> BadRequest ..On ne peut pas modifier un magazin qui n'existe pas
        if (!demandeRepository.existsById(demande.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Demande demandeToUpdate = demandeRepository.saveAndFlush(demande);

        return ResponseEntity.ok().body(demandeToUpdate);

    }

    @ApiOperation(value = "cette ressource permet de supprimer une demande")
    @DeleteMapping(value = "/supprimer-demande/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable("id") Long id){

        List<DemandeProduit> listProduitByEtatId;
        listProduitByEtatId = demandeProduitRepository.findByDemande_Id(id);

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!listProduitByEtatId.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!demandeRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        demandeRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }



    @ApiOperation(value = "cette ressource permet d'obtenir les stats des demandes par jour.semaine.mois.annee")
    @GetMapping(value = "/getStats-day-week-mounth-year")
    public ResponseEntity<List<Integer>> getStatsDemande(){

        int dayCount, weekCount, mounthCount, yearCount;

        List<Demande> all_demande = demandeRepository.findAll();

        dayCount = (int) all_demande.stream().filter(demande -> isDateInCurrentDate(demande.getCreatedDate())).count();
        weekCount = (int) all_demande.stream().filter(demande -> isDateInCurrentWeek(demande.getCreatedDate())).count();
        mounthCount = (int) all_demande.stream().filter(demande -> isDateInCurrentMonth(demande.getCreatedDate())).count();
        yearCount = (int) all_demande.stream().filter(demande -> isDateInCurrentYear(demande.getCreatedDate())).count();

        List<Integer> statsParam = new ArrayList<>();
        statsParam.add(dayCount);
        statsParam.add(weekCount);
        statsParam.add(mounthCount);
        statsParam.add(yearCount);

        return ResponseEntity.status(HttpStatus.OK).body(statsParam);
    }


    public boolean isDateInCurrentDate(Instant createdDate) {
        Date date = Date.from(createdDate);
        Calendar currentCalendar = Calendar.getInstance();
        int currentDay = currentCalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetDay = targetCalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        //return targetCalendar.getTime().equals(currentCalendar.getTime());
        return currentDay == targetDay;
    }

    public boolean isDateInCurrentWeek(Instant createdDate) {
        Date date = Date.from(createdDate);
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return week == targetWeek && year == targetYear;
    }

    public boolean isDateInCurrentMonth(Instant createdDate) {
        Date date = Date.from(createdDate);
        Calendar currentCalendar = Calendar.getInstance();
        int month = currentCalendar.get(Calendar.MONTH);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetMonth = targetCalendar.get(Calendar.MONTH);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return month == targetMonth && year == targetYear;
    }

    public boolean isDateInCurrentYear(Instant createdDate) {
        Date date = Date.from(createdDate);
        Calendar currentCalendar = Calendar.getInstance();
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return year == targetYear;
    }

}
