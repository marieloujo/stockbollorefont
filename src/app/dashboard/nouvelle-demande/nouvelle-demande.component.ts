import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../services/common/behavior.service';
import {FormGroup} from '@angular/forms';
import {Produit} from '../../models/produit';
import {ProduitService} from '../../services/dashboard/produit.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Personne} from '../../models/personne';
import {PersonneService} from '../../services/dashboard/personne.service';

@Component({
  selector: 'app-nouvelle-demande',
  templateUrl: './nouvelle-demande.component.html',
  styleUrls: ['./nouvelle-demande.component.css']
})
export class NouvelleDemandeComponent implements OnInit {

  validateNewDemandeForm!: FormGroup;

  produitList: Produit[];
  personneList: Personne[];

  produitChoice: Produit;

  modele: string;
  gamme: string;
  marque: string;

  indexOfTab: number;

  listOfColumn: any = [];

  countNew: number = 0;

  constructor(
    private behaviorService: BehaviorService,
    private produitService: ProduitService,
    private personneService: PersonneService,
  ) { }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Demande', 'Nouvelle demande']);

    this.listProduit();

    this.listPersonne();

  }

  listProduit(): void {
    this.produitService.getList().subscribe(
      (data: Produit[]) => {
        this.produitList = [...data];
        console.log('Produit List ==>', this.produitList);
        //this.listOfDisplayData = [...this.produitList];
        //this.pageIndex = 1;
      },
      (error: HttpErrorResponse) => {
        console.log('error getList Produit ==>', error.message, ' ', error.status, ' ', error.statusText);
      });
  }

  listPersonne(): void {
    this.personneService.getList().subscribe(
      (data: Personne[]) => {
        this.personneList = [...data];
        console.log('Personne List ==>', this.personneList);
        //this.listOfDisplayData = [...this.produitList];
        //this.pageIndex = 1;
      },
      (error: HttpErrorResponse) => {
        console.log('error getList Personne ==>', error.message, ' ', error.status, ' ', error.statusText);
      });
  }

  loadDetailProduit(){
    this.marque = this.produitChoice.marque.libelle;
    this.gamme = this.produitChoice.gamme.libelle;
    this.modele = this.produitChoice.modele.libelle;

    console.log(this.marque);
    console.log(this.gamme);
    console.log(this.modele);

    console.log(this.produitChoice);

  }

  validerProduit(){
    //this.indexOfTab = 1;
    this.countNew++;
  }

  goToListDemandeProduit(){
    this.indexOfTab = 1;
  }

  addNewProduit(){
    this.indexOfTab = 0;
  }



}
