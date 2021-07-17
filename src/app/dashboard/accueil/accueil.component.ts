import {Component, OnInit} from '@angular/core';
import {BehaviorService} from '../../services/common/behavior.service';
import {DemandeProduitService} from '../../services/dashboard/demande-produit.service';
import {DemandeProduit} from '../../models/demande-produit';
import {Marque} from '../../models/marque';
import {HttpErrorResponse} from '@angular/common/http';
import {ProduitService} from '../../services/dashboard/produit.service';
import {DemandeService} from '../../services/dashboard/demande.service';
import {Produit} from '../../models/produit';
import {Demande} from '../../models/demande';
import {TokenService} from 'src/app/services/token/token.service';
import {Token} from 'src/app/models/token.model';
import {environment} from '../../../environments/environment';
import {GammeService} from '../../services/dashboard/gamme.service';
import {Etat} from '../../models/etat';
import {EtatService} from '../../services/dashboard/etat.service';
import {EtatProduitService} from '../../services/dashboard/etat-produit.service';
import {EtatProduit} from '../../models/etat-produit';
import {DemandeStatus} from '../../enumerations/demande-status.enum';
import { ProduitStatus } from 'src/app/enumerations/produit-status.enum';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { ProduitEtat } from 'src/app/enumerations/produit-etat.enum';
import {DemandeRetourForm} from "../../models/demande-retour-form";

interface StatsPer_dayWeekMonthYear {
  day: number;
  week: number;
  month: number;
  year: number;
}


@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {

  demandeProduitDescList: DemandeProduit[] = [];

  listOfColumn: any = [];
  listOfDisplayData = [];
  token: Token;
  environment = environment;
  gestionnaire: boolean;
  demandeur: boolean;
  validateur: boolean;
  etatList: Etat[] = [];
  etatProduitList: EtatProduit[] = [];
  etatProduitListSortByProduit: EtatProduit[] = [];
  DemandeStatusEnum = DemandeStatus;
  ProduitStatus = ProduitStatus;
  public ProduitEtat = ProduitEtat;

  statsCOunt: StatsPer_dayWeekMonthYear = new class implements StatsPer_dayWeekMonthYear {
    day: number;
    month: number;
    week: number;
    year: number;
  };

  constructor(
    private behaviorService: BehaviorService,
    public produitService: ProduitService,
    private demandeService: DemandeService,
    public tokenService: TokenService,
    private gammeService: GammeService,
    private etatService: EtatService,
    private etatProduitService: EtatProduitService,
    public demandeProduitService: DemandeProduitService,
  ) {
    this.token = this.tokenService.getAccessToken();
  }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Tableau de bord']);

    this.listDemandeProduitDescCreatedDate();

    this.listOfColumnHeader();

    this.getStatsOfDemande();

    this.demandeur = this.canLivrer();
    this.validateur = this.canValider();
    this.gestionnaire = this.canMettreDisposition();
    console.log(this.gestionnaire + ' ' + this.validateur);

    this.listEtat();
    this.listEtatProduit();

  }

  listEtat(): void {
    this.etatService.getList().subscribe(
      (data: Etat[]) => {
        this.etatList = [...data];
        console.log('EtatList ==>', this.etatList);
      },
      (error: HttpErrorResponse) => {
        console.log('error getList etat ==>', error.message, ' ', error.status, ' ', error.statusText);
      });
  }

  listEtatProduit(): void {
    this.etatProduitService.getList().subscribe(
      (data: EtatProduit[]) => {
        this.etatProduitList = [...data];
        console.log('Etat Produit List ==>', this.etatProduitList);
      },
      (error: HttpErrorResponse) => {
        console.log('error getList etat Produit ==>', error.message, ' ', error.status, ' ', error.statusText);
      });
  }

  getStatsOfDemande() {
    this.demandeService.getStatsDayWeekMonthYear().subscribe(
      (data: any) => {
        console.log('Les stats ! => ');
        console.log(data);
        this.statsCOunt.day = data[0];
        this.statsCOunt.week = data[1];
        this.statsCOunt.month = data[2];
        this.statsCOunt.year = data[3];
      },
      (error: any) => {

      }
    );

  }

  canValider(): boolean {
    return this.token.roles.indexOf(environment.ROLE_VALIDATEUR) > -1;
  }


  canLivrer(): boolean {
    return this.token.roles.indexOf(environment.ROLE_DEMANDEUR) > -1;
  }

  canMettreDisposition(): boolean {
    return this.token.roles.indexOf(environment.ROLE_GESTIONNAIRE) > -1;
  }



  livrerDemande(id: number): void {
    this.demandeProduitService.livrerDemande(id).subscribe(
        (data: Demande) => {
        console.log('Demande livree  ==>', data);
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log('error ==>', error.message, ' ', error.status, ' ', error.statusText);
      });

  }

  validerDemande(id: number): void {
    this.demandeProduitService.validerDemande(id).subscribe(
        (data: Demande) => {
        console.log('Demande validee  ==>', data);
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log('error ==>', error.message, ' ', error.status, ' ', error.statusText);
      });

  }

  misADisposition(id: number): void {
    this.demandeProduitService.misADisposition(id).subscribe(
        (data: Demande) => {
        console.log('Demande validee  ==>', data);
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log('error ==>', error.message, ' ', error.status, ' ', error.statusText);
      });

  }

  confirmationReceptionDemande(id: number): void {
    this.demandeProduitService.confirmationReceptionDemande(id).subscribe(
        (data: Demande) => {
        console.log('Demande confirmationReceptionDemande  ==>', data);
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log('error ==>', error.message, ' ', error.status, ' ', error.statusText);
      });

  }
  




  validerRetourDemande(demandeProduit: DemandeProduit): void {
    const demandeProduitForm: DemandeRetourForm = {
      etatProduitRetour: demandeProduit.etatProduitRetour,
      demandeProduitId: demandeProduit.id,
      canValidate: this.tokenService.isGestionnaire()
    };
    this.demandeProduitService.retourValiderDemande(demandeProduitForm).subscribe(
        (data: Demande) => {
        console.log('Demande validee  ==>', data);
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log('error ==>', error.message, ' ', error.status, ' ', error.statusText);
      });

  }

  rejeterDemande(id: number): void {
    this.demandeProduitService.rejeterDemande(id).subscribe(
      (data: Demande) => {
        console.log('Demande rejetee  ==>', data);
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log('error ==>', error.message, ' ', error.status, ' ', error.statusText);
      });

  }


  rejeterRetourDemande(demandeProduit: DemandeProduit): void {
    const demandeProduitForm: DemandeRetourForm = {
      etatProduitRetour: demandeProduit.etatProduitRetour,
      demandeProduitId: demandeProduit.id,
      canValidate: this.tokenService.isGestionnaire()
    };
    this.demandeProduitService.retourRejeterDemande(demandeProduitForm).subscribe(
      (data: Demande) => {
        console.log('Demande rejetee  ==>', data);
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log('error ==>', error.message, ' ', error.status, ' ', error.statusText);
      });

  }

  listDemandeProduitDescCreatedDate(): void {
    this.demandeProduitService.getListDescCreateDate().subscribe(
      (data: DemandeProduit[]) => {
        this.demandeProduitDescList = data;
        console.log('Demande Produit Desc Date ==>', this.demandeProduitDescList);

        for (let dmdProd of this.demandeProduitDescList) {

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

        this.listOfDisplayData = [...this.demandeProduitDescList];

        console.log('DemandeProduit listOfDisplayData ==>', this.listOfDisplayData);

      },
      (error: HttpErrorResponse) => {
        console.log('error getList marque ==>', error.message, ' ', error.status, ' ', error.statusText);
      });
  }

  listOfColumnHeader(): void {
    this.listOfColumn = [
      {
        title: 'Date de la demande',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.demande.dateHeure.toString().localeCompare(b.demande.dateHeure.toString()),
        //sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.numSerie - b.produit.numSerie,
      },
      {
        title: 'Date de retour',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.dateValidationRetour.toString().localeCompare(b.dateValidationRetour.toString()),
        //sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.numSerie - b.produit.numSerie,
      },
      {
        title: 'Numero Série',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.numSerie.localeCompare(b.produit.numSerie),
        //sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.numSerie - b.produit.numSerie,
      },
      {
        title: 'Equipement',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.gamme.libelle.localeCompare(b.produit.gamme.libelle),
      },
      {
        title: 'Marque',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.marque.libelle.localeCompare(b.produit.marque.libelle),
      },
      {
        title: 'Modele',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.modele.libelle.localeCompare(b.produit.modele.libelle),
      },
      {
        title: 'Statut',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.status.localeCompare(b.produit.status),
      },
      {
        title: 'Etat',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.produit.etat?.libelle?.localeCompare(b.produit.etat?.libelle),
      },
      {
        title: 'Demandeur',
        compare: null,
        sortFn: (a: DemandeProduit, b: DemandeProduit) => a.demande.personne.nom.localeCompare(b.demande.personne.nom),
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


  /**
   *
   * @param data
   */
  public canValidateOrCancel(data: DemandeProduit): boolean {
    // soit le demandeur de la demande || soit le gestionnaire
    return (data.personne.id === this.tokenService.getAccessToken().id);
  }

}
