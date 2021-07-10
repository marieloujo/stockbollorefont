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
import {Gamme} from '../../models/gamme';
import {Marque} from '../../models/marque';
import {Modele} from '../../models/modele';
import {GammeService} from '../../services/dashboard/gamme.service';
import {MarqueService} from '../../services/dashboard/marque.service';
import {ModeleService} from '../../services/dashboard/modele.service';
import {Etat} from '../../models/etat';
import {ProduitEtat} from '../../enumerations/produit-etat.enum';
import {DemandeRetourForm} from '../../models/demande-retour-form';
import {NzNotificationService} from 'ng-zorro-antd/notification';

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

  personneList: Personne[];
  gammeList: Array<Gamme> = [];
  marqueList: Array<Marque> = [];
  modeleList: Array<Modele> = [];
  public currentMarqueSelected: Marque;
  public currentEquipementSelected: Gamme;
  public currentPersonneSelected: Personne;
  public currentModelSelected: Modele;
  public demandeProduitSelected: DemandeProduit;
  public canShowModalForSubmission = false;
  public isOkLoading = false;
  public nouvelEtatProduit: string;

  constructor(
      private behaviorService: BehaviorService,
      private demandeProduitService: DemandeProduitService,
      private produitService: ProduitService,
      private demandeService: DemandeService,
      private personneService: PersonneService,
      private gammeService: GammeService,
      private marqueService: MarqueService,
      private modeleService: ModeleService,
      public tokenService: TokenService,
      private nzNotificationService: NzNotificationService
  ) {
    this.token = tokenService.getAccessToken();
  }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Demande', 'Demande retour']);
    this.initData();
  }

  initData(): void {
    this.listPersonne();
    this.listGamme();
    this.listMarque();
    this.listModele();
    this.getListForRetour();
    this.listOfColumnHeader();
    this.can_create = this.canCreate();
    this.nouvelEtatProduit = null;
    this.demandeProduitSelected = null;
  }

  listMarque(): void {
    this.marqueService.getList().subscribe(
        (data: Marque[]) => {
          this.marqueList = data;
          console.log('MarqueList ==>', this.marqueList);
        },
        (error: HttpErrorResponse) => {
          console.log('error getList marque ==>', error.message, ' ', error.status, ' ', error.statusText);
        });
  }

  listModele(): void {
    this.modeleService.getList().subscribe(
        (data: Modele[]) => {
          this.modeleList = data;
          console.log('ModeleList ==>', this.modeleList);
        },
        (error: HttpErrorResponse) => {
          console.log('error getList modele ==>', error.message, ' ', error.status, ' ', error.statusText);
        });
  }

  listGamme(): void {
    this.gammeService.getList().subscribe(
        (data: Gamme[]) => {
          this.gammeList = data;
          console.log('GammeList ==>', this.gammeList);
        },
        (error: HttpErrorResponse) => {
          console.log('error getList Gamme ==>', error.message, ' ', error.status, ' ', error.statusText);
        });
  }

  listPersonne(): void {
    this.personneService.getList().subscribe(
        (data: Personne[]) => {
          this.personneList = [...data];
          console.log('Personne List ==>', this.personneList);
          // this.listOfDisplayData = [...this.produitList];
          // this.pageIndex = 1;
        },
        (error: HttpErrorResponse) => {
          console.log('error getList Personne ==>', error.message, ' ', error.status, ' ', error.statusText);
        });
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



  personneSelectChange(personne: Personne): void {
    this.currentPersonneSelected = personne;
    console.log('personne selected');
    console.log(this.currentPersonneSelected);
    this.listOfDisplayData = this.applyFiltre();
  }

  equipementSelectChange(equipement: Gamme): void {
    this.currentEquipementSelected = equipement;
    console.log('gamme selected');
    console.log(this.currentEquipementSelected);
    this.listOfDisplayData = this.applyFiltre();
  }
  modelSelectChange(model: Modele): void {
    this.currentModelSelected = model;
    console.log('model selected');
    console.log(this.currentModelSelected);
    this.listOfDisplayData = this.applyFiltre();
  }

  marqueSelectChange(marque: Marque): void {
    this.currentMarqueSelected = marque;
    console.log('marque selected');
    console.log(this.currentMarqueSelected);
    this.listOfDisplayData = this.applyFiltre();
  }


  // apply filtre on demandeProduitList
  applyFiltre(): Array<DemandeProduit> {
    let result: Array<DemandeProduit> = [...this.demandeProduitList];
    // personne
    if (![null, undefined].includes(this.currentPersonneSelected)) {
      result = result.filter(demandeProduit => demandeProduit.personne.id === this.currentPersonneSelected.id);
    }
    // equipement
    if (![null, undefined].includes(this.currentEquipementSelected)) {
      result = result.filter(demandeProduit => demandeProduit.gamme.id === this.currentEquipementSelected.id);
    }
    // model
    if (![null, undefined].includes(this.currentModelSelected)) {
      result = result.filter(demandeProduit => demandeProduit.modele.id === this.currentModelSelected.id);
    }// marque
    if (![null, undefined].includes(this.currentMarqueSelected)) {
      result = result.filter(demandeProduit =>  demandeProduit.marque.id === this.currentMarqueSelected.id);
    }

    // return result
    return result;
  }

  /**
   *
   * @param demandeProduit
   */
  showModalForSubmission(demandeProduit: DemandeProduit): void {
    this.nouvelEtatProduit = null;
    this.canShowModalForSubmission = true;
    this.demandeProduitSelected = demandeProduit;
  }

  handleOk(): void {
    const demandeRetourForm: DemandeRetourForm = {
      demandeProduitId: this.demandeProduitSelected.id,
      etatProduitRetour: this.nouvelEtatProduit,
      canValidate: this.tokenService.isGestionnaire()
    };
    this.saveDemandeRetour(demandeRetourForm);
  }

  handleCancel(): void {
    this.canShowModalForSubmission = false;
  }

  nouvelEtatProduitEnumChange(newEtat: string): void {
    this.nouvelEtatProduit = newEtat;
    console.log(this.nouvelEtatProduit);
    // todo: more task
  }


  saveDemandeRetour(demandeRetourForm: DemandeRetourForm): void {
    this.isOkLoading = true;
    this.demandeProduitService.retourCreateDemande(demandeRetourForm).subscribe(
        (data: DemandeProduit) => {
          console.log('new demande retour created ==>', data);
          this.isOkLoading = false;
          this.canShowModalForSubmission = false;
          let messageContent = 'Demande retour enregistrée avec succès.';
          if (this.tokenService.isGestionnaire()) {
            messageContent = 'Etat du produit mis à jour et déposé en stock avec succès.';
          }
          this.nzNotificationService.success('Demande retour', messageContent,
              {
                nzDuration: 50000
              });
          this.initData();
        },
        (error: HttpErrorResponse) => {
          console.log('error create demande retour ==>', error.message, ' ', error.status, ' ', error.statusText);
        });
  }

}
