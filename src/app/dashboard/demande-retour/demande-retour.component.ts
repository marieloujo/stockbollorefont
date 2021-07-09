import { Component, OnInit } from '@angular/core';
import {DemandeProduit} from '../../models/demande-produit';
import {Personne} from '../../models/personne';
import {Token} from '../../models/token.model';
import {BehaviorService} from '../../services/common/behavior.service';
import {DemandeProduitService} from '../../services/dashboard/demande-produit.service';
import {ProduitService} from '../../services/dashboard/produit.service';
import {DemandeService} from '../../services/dashboard/demande.service';
import {PersonneService} from '../../services/dashboard/personne.service';
import {TokenService} from '../../services/token/token.service';
import {environment} from '../../../environments/environment';
import {Produit} from '../../models/produit';
import {HttpErrorResponse} from '@angular/common/http';
import {Demande} from '../../models/demande';

@Component({
  selector: 'app-demande-retour',
  templateUrl: './demande-retour.component.html',
  styleUrls: ['./demande-retour.component.css']
})
export class DemandeRetourComponent implements OnInit {

  demandeProduitList: Array<DemandeProduit> = [];

  listOfColumn: any = [];
  listOfDisplayData;

  visibleDrawer = false;

  token: Token;
  can_create: boolean;

  searchValueNumero = '';
  searchValueEquipement = '';
  searchValueMarque = '';
  searchValueModele = '';
  searchValueDemandeur = '';

  visibleNumero = false;
  visibleEquipement = false;
  visibleMarque = false;
  visibleModele = false;
  visibleDemandeur = false;

  dateToShow = '';
  timeToShow = '';

  startDate: Date;
  endDate: Date;

  public canShowModal = false;

  constructor(
      private behaviorService: BehaviorService,
      private demandeProduitService: DemandeProduitService,
      private produitService: ProduitService,
      private demandeService: DemandeService,
      private personneService: PersonneService,
      private tokenService: TokenService,
  ) {
    this.token = tokenService.getAccessToken();
  }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Demande', 'Demande retour']);

    this.getListForRetour();

    this.listOfColumnHeader();

    this.can_create = this.canCreate();

  }


  canCreate(): boolean {
    return (this.token.roles.indexOf(environment.ROLE_ADMIN) > -1) || (this.token.roles.indexOf(environment.ROLE_DEMANDEUR) > -1) || (this.token.roles.indexOf(environment.ROLE_VALIDATEUR) > -1);
  }


  getListForRetour(): void {
    this.demandeProduitService.getListForRetour().subscribe(
        (data: DemandeProduit[]) => {
          this.demandeProduitList = data;
          console.log('DemandeProduit List ==>', this.demandeProduitList);

          for (const dmdProd of this.demandeProduitList) {

            this.produitService.getProduitById(dmdProd.produit.id).subscribe(
                (dataProd: Produit) => {
                  dmdProd.gamme = dataProd.gamme;
                  dmdProd.marque = dataProd.marque;
                  dmdProd.modele = dataProd.modele;
                },
                (error: HttpErrorResponse) => {
                  console.log('error get by id Produit ==>', error.message, ' ', error.status, ' ', error.statusText);
                });

            this.demandeService.getDemandeById(dmdProd.demande.id).subscribe(
                (dataDem: Demande) => {
                  dmdProd.personne = dataDem.personne;
                },
                (error: HttpErrorResponse) => {
                  console.log('error get by id Produit ==>', error.message, ' ', error.status, ' ', error.statusText);
                });

          }

          this.listOfDisplayData = [...this.demandeProduitList];

          console.log('DemandeProduit listOfDisplayData ==>', this.listOfDisplayData);

          // this.pageIndex = 1;
        },
        (error: HttpErrorResponse) => {
          console.log('error getList DemandeProduit ==>', error.message, ' ', error.status, ' ', error.statusText);
        });
  }

  listOfColumnHeader() {
    this.listOfColumn = [
      {
        title: 'Numero Série',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.numSerie.localeCompare(b.produit.numSerie),
        // sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.numSerie - b.produit.numSerie,
      },
      {
        title: 'Equipement',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.gamme.libelle.localeCompare(b.gamme.libelle),
      },
      {
        title: 'Marque',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.marque.libelle.localeCompare(b.marque.libelle),
      },
      {
        title: 'Modele',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.modele.libelle.localeCompare(b.modele.libelle),
      },
      {
        title: 'Personne concernée',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.personne.nom.localeCompare(b.personne.nom),
      },
      /*{
        title: 'Math Score',
        compare: (a: DataItem, b: DataItem) => a.math - b.math,
        priority: 2
      },
      {
        title: 'English Score',
        compare: (a: DataItem, b: DataItem) => a.english - b.english,
        priority: 1
      }*/
    ];
  }




  openDrawer(data: DemandeProduit): void {

    this.visibleDrawer = true;
  }


  closeDrawer(): void {
    this.visibleDrawer = false;
  }


  resetNumero(): void {
    this.searchValueNumero = '';
    this.searchNumero();
  }

  searchNumero(): void {
    this.visibleNumero = false;
    this.listOfDisplayData = this.demandeProduitList.filter((item: DemandeProduit) => item.produit.numSerie.indexOf(this.searchValueNumero) !== -1);
  }

  resetEquipement(): void {
    this.searchValueEquipement = '';
    this.searchEquipement();
  }

  searchEquipement(): void {
    this.visibleEquipement = false;
    this.listOfDisplayData = this.demandeProduitList.filter((item: DemandeProduit) => item.gamme.libelle.indexOf(this.searchValueEquipement) !== -1);
  }

  resetMarque(): void {
    this.searchValueMarque = '';
    this.searchMarque();
  }

  searchMarque(): void {
    this.visibleMarque = false;
    this.listOfDisplayData = this.demandeProduitList.filter((item: DemandeProduit) => item.marque.libelle.indexOf(this.searchValueMarque) !== -1);
  }

  resetModele(): void {
    this.searchValueModele = '';
    this.searchModele();
  }

  searchModele(): void {
    this.visibleModele = false;
    this.listOfDisplayData = this.demandeProduitList.filter((item: DemandeProduit) => item.modele.libelle.indexOf(this.searchValueModele) !== -1);
  }

  resetDemandeur(): void {
    this.searchValueDemandeur = '';
    this.searchDemandeur();
  }

  searchDemandeur(): void {
    this.visibleDemandeur = false;
    this.listOfDisplayData = this.demandeProduitList.filter((item: DemandeProduit) => item.personne.nom.indexOf(this.searchValueDemandeur) !== -1 || item.personne.prenom.indexOf(this.searchValueDemandeur) !== -1);
  }


}
