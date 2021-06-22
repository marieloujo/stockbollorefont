import {Component, OnInit} from '@angular/core';
import {BehaviorService} from '../../services/common/behavior.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Produit} from '../../models/produit';
import {ProduitService} from '../../services/dashboard/produit.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Personne} from '../../models/personne';
import {PersonneService} from '../../services/dashboard/personne.service';
import {NewDemande} from '../../models/new-demande';
import {Marque} from '../../models/marque';
import {Demande} from '../../models/demande';
import {DemandeProduit} from '../../models/demande-produit';
import {MouvementService} from '../../services/common/mouvement.service';
import {Mouvement} from '../../models/Mouvement';
import {DemandeService} from '../../services/dashboard/demande.service';
import {DemandeProduitService} from '../../services/dashboard/demande-produit.service';
import { Router } from '@angular/router';
import {Etat} from '../../models/etat';
import {EtatService} from '../../services/dashboard/etat.service';
import {Gamme} from '../../models/gamme';
import {EtatProduit} from '../../models/etat-produit';
import {EtatProduitService} from '../../services/dashboard/etat-produit.service';
import {GammeService} from '../../services/dashboard/gamme.service';
import {Modele} from '../../models/modele';
import {MarqueService} from '../../services/dashboard/marque.service';
import {ModeleService} from '../../services/dashboard/modele.service';
import { NzSwitchModule } from 'ng-zorro-antd/switch';
import {MagasinProduit} from "../../models/magasin-produit";
import {MagasinProduitService} from "../../services/dashboard/magasin-produit.service";

@Component({
  selector: 'app-nouvelle-demande',
  templateUrl: './nouvelle-demande.component.html',
  styleUrls: ['./nouvelle-demande.component.css']
})
export class NouvelleDemandeComponent implements OnInit {
  constructor(
    private behaviorService: BehaviorService,
    private produitService: ProduitService,
    private personneService: PersonneService,
    private mouvementService: MouvementService,
    private demandeService: DemandeService,
    private demandeProduitService: DemandeProduitService,
    private fb: FormBuilder,
    private router: Router,
    private etatService: EtatService,
    private etatProduitService: EtatProduitService,
    private magasinProduitService: MagasinProduitService,
    private gammeService: GammeService,
    private marqueService: MarqueService,
    private modeleService: ModeleService,
  ) {
  }

  validateNewDemandeForm!: FormGroup;

  produitList: Produit[];
  personneList: Personne[];
  newDemandeList: NewDemande[];
  mouvementList: Mouvement[];
  demandeProduitList: DemandeProduit[] = [];
  etatList: Etat[];

  produitChoice: Produit;
  personneDemande: Personne;
  mouvementDemande: any;
  // etatSelectedValue: Etat;
  etatSelectedValue;

  modele: string;
  gamme: string;
  marque: string;

  indexOfTab: number;

  listOfColumn: any = [];

  countNew = 0;

  showFieldEtat = false;

  myProduitSelected: Array<Produit> = [];
  myProduitListToSelected: Array<Produit> = [];
  listOfColumnProduitToBeSelected: any = [];
  gammeList: Array<Gamme> = [];
  marqueList: Array<Marque> = [];
  modeleList: Array<Modele> = [];

  // compareFn = (o1: any, o2: any) => (o1 && o2 ? o1.value === o2.value : o1 === o2);
  compareFn = (o1: any, o2: any) => (o1 && o2 ? o1.id === o2.id : o1 === o2);

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Demande', 'Nouvelle demande']);

    this.makeDemandeForm(null);

    this.listPersonne();

    this.listOfColumnHeader();

    this.listMouvement();

    this.listEtat();

    this.listOfColumnProduitToBeSelectedHeader();

    this.listGamme();

    this.listMarque();

    this.listModele();

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

  listEtat(): void {
    this.etatService.getList().subscribe(
      (data: Etat[]) => {
        this.etatList = [...data];
        console.log('EtatList ==>', this.etatList);
        this.listProduits();
      },
      (error: HttpErrorResponse) => {
        console.log('error getList etat ==>', error.message, ' ', error.status, ' ', error.statusText);
      });
  }

  listProduits(): void {
    this.produitService.getList().subscribe(
        (data: Produit[]) => {
          this.produitList = [...data];
          console.log('Produit List ==>', this.produitList);

          for (const prod of this.produitList) {
            // let eP: EtatProduit[] = [];
            console.log('le produit ==> ');
            console.log(prod);
            // let etatProd: EtatProduit = prod.etatProduits.find(p => p.actuel == true);

            console.log('Le dernier -----');
            console.log(prod.etatProduits[prod.etatProduits.length - 1]);
            const etatProd: EtatProduit = prod.etatProduits[prod.etatProduits.length - 1];

            console.log(etatProd);

            this.etatProduitService.getEtatProduitById(etatProd?.id).subscribe(
                (dataEtatProd: EtatProduit) => {
                  console.log('DataEtatProd');
                  console.log(dataEtatProd.etat.libelle);
                  prod.etat = dataEtatProd.etat;
                },
                (error: HttpErrorResponse) => {
                  console.log('error get by id etatProduit ==>', error.message, ' ', error.status, ' ', error.statusText);
                });

            const magProd: MagasinProduit = prod.magazinProduits.find(m => m.actuel == true);
            this.magasinProduitService.getMagasinProduitById(magProd?.id).subscribe(
                (dataMagProd: MagasinProduit) => {
                  console.log('DataMagProd');
                  console.log(dataMagProd.magazin.libelle);
                  prod.magasin = dataMagProd.magazin;
                },
                (error: HttpErrorResponse) => {
                  console.log('error get by id magasinProduit ==>', error.message, ' ', error.status, ' ', error.statusText);
                });
          }

          this.produitList = [...this.produitList];
          console.log('@@@@@@@@@@@@');
          console.log(this.produitList);
          console.log('@@@@@@@@@@@@');
          // this.pageIndex = 1;
        },
        (error: HttpErrorResponse) => {
          console.log('error getList Produit ==>', error.message, ' ', error.status, ' ', error.statusText);
        });
  }

  listMouvement(): void {
    this.mouvementService.getList().subscribe(
      (data: Mouvement[]) => {
        this.mouvementList = [...data];
        console.log('Mouvement List ==>', this.mouvementList);

      },
      (error: HttpErrorResponse) => {
        console.log('error getList Mouvement ==>', error.message, ' ', error.status, ' ', error.statusText);
      });
  }

  /*makeDemandeForm(produit: Produit, demande: Demande){
    this.validateNewDemandeForm = this.fb.group({
      //id: [produit != null ? produit.id : null],
      numSerie: [produit != null ? produit.numSerie : null,
        [Validators.required]],
      modele: [produit != null ? produit.modele : null,
        [Validators.required]],
      marque: [produit != null ? produit.marque : null,
        [Validators.required]],
      gamme: [produit != null ? produit.gamme : null,
        [Validators.required]],
      personne: [demande != null ? demande.personne : null,
        [Validators.required]],
      description: [produit != null ? produit.description : null],

    });
  }*/

  makeDemandeForm(demandeProduit: DemandeProduit) {
    this.validateNewDemandeForm = this.fb.group({
      id: [demandeProduit != null ? demandeProduit.id : null],
     // produit: [demandeProduit != null ? demandeProduit.produit : null, [Validators.required]],
     // demande: [demandeProduit != null ? demandeProduit.demande : null],
      /*personne: [demandeProduit != null ? demandeProduit.demande.personne : null, {value: this.countNew, disabled: true},
        [Validators.required]],*/
      personne: [demandeProduit != null ? demandeProduit.demande.personne : null,
        [Validators.required]],
      description: [demandeProduit != null ? demandeProduit.description : null],
      valider: [demandeProduit != null ? demandeProduit.valider : null],
      livrer: [demandeProduit != null ? demandeProduit.livrer : null],
      // mouvement: [demandeProduit != null ? demandeProduit.demande.mouvement : null, {value: this.countNew, disabled: true}],
     // mouvement: [demandeProduit != null ? demandeProduit.demande.mouvement : null],
      etat: [demandeProduit != null ? demandeProduit.etat : null],
      gamme: [],
      model: [],
      marque: [],
    });
  }

  resetDemandeForm(e: MouseEvent): void {
    e.preventDefault();
    this.validateNewDemandeForm.reset();
    for (const key in this.validateNewDemandeForm.controls) {
      this.validateNewDemandeForm.controls[key].markAsPristine();
      this.validateNewDemandeForm.controls[key].updateValueAndValidity();
    }
    this.makeDemandeForm(null);
    this.marque = '';
    this.gamme = '';
    this.modele = '';
    this.indexOfTab = 0;
    // this.pageIndex = 1;
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

  loadDetailProduit() {
    /*this.marque = this.produitChoice.marque.libelle;
    this.gamme = this.produitChoice.gamme.libelle;
    this.modele = this.produitChoice.modele.libelle;*/

    const produitToChoice = this.validateNewDemandeForm.get('produit').value;

    this.marque = produitToChoice?.marque.libelle;
    this.gamme = produitToChoice?.gamme.libelle;
    this.modele = produitToChoice?.modele.libelle;

    console.log(this.marque);
    console.log(this.gamme);
    console.log(this.modele);

    console.log(produitToChoice);

    console.log(this.etatSelectedValue);

  }

  loadStateFieldEtat(event: any){
    /*const mouvementChoice = this.validateNewDemandeForm.get('mouvement').value;
    console.log(mouvementChoice);*/
    console.log(event);
    const mouv = this.mouvementList.find(mv => mv.value == event);
    if (event == 'ENTRER') { this.showFieldEtat = true ; }
    else { this.showFieldEtat = false; }
  }

  validerProduit() {
    // this.indexOfTab = 1;

    for (const i in this.validateNewDemandeForm.controls) {
      this.validateNewDemandeForm.controls[i].markAsDirty();
      this.validateNewDemandeForm.controls[i].updateValueAndValidity();
    }

    if (this.validateNewDemandeForm.valid) {

      const formData = this.validateNewDemandeForm.value;
      this.personneDemande = formData.personne;
      this.mouvementDemande = formData.mouvement;
      formData.valider = false;
      formData.livrer = false;
      console.log('FormData -- Formulaire valide');
      console.log(formData);
      if (formData.id == null) {
        this.demandeProduitList.push(formData);
        this.demandeProduitList = [...this.demandeProduitList];
        console.log('demandeProduitList ==> ');
        console.log(this.demandeProduitList);

        this.makeDemandeForm(null);
        this.marque = '';
        this.gamme = '';
        this.modele = '';

        this.countNew++;
      }

      if (this.countNew == 1 || this.demandeProduitList.length == 1){
        const demandeProduitFixed: DemandeProduit = new DemandeProduit();
        this.validateNewDemandeForm.get('personne').setValue(this.personneDemande);
        this.validateNewDemandeForm.get('mouvement').setValue(this.mouvementDemande);
        // this.makeDemandeForm(null);
      }

    }

  }

  goToListDemandeProduit() {
    this.indexOfTab = 1;
  }

  addNewProduit() {
    this.indexOfTab = 0;
  }


  confirmMsgDelete(data: any) {

    console.log('data dans confirm suppression ');
    console.log(data);
    console.log(this.demandeProduitList.length);
    this.demandeProduitList = this.demandeProduitList.filter(dp => dp !== data);
    this.demandeProduitList = [...this.demandeProduitList];
    console.log(this.demandeProduitList.length);
    this.countNew -= 1;
    this.makeDemandeForm(null);


  }

  cancelMsgDelete(): void {
    // this.nzMessageService.info('click confirm');
  }


  myValiderSelectionProduit(): void {
    for (const i in this.validateNewDemandeForm.controls) {
      this.validateNewDemandeForm.controls[i].markAsDirty();
      this.validateNewDemandeForm.controls[i].updateValueAndValidity();
    }
    if (this.validateNewDemandeForm.valid) {
      this.myProduitSelected.forEach(produitSelected => {
        const formData = this.validateNewDemandeForm.value;
        this.personneDemande = formData.personne;
        this.mouvementDemande = formData.mouvement;
        formData.valider = false;
        formData.livrer = false;
        formData.produit = produitSelected;
        console.log('FormData -- Formulaire valide');
        console.log(formData);
        if (formData.id == null) {
          this.demandeProduitList.push(formData);
          this.demandeProduitList = [...this.demandeProduitList];
          console.log('demandeProduitList ==> ');
          console.log(this.demandeProduitList);
          this.countNew ++;
        }
      });
      this.makeDemandeForm(null);
      this.marque = '';
      this.gamme = '';
      this.modele = '';
      this.myProduitListToSelected = [];
      this.goToListDemandeProduit();
    }

  }


  faireValiderProduit(){

    if (this.demandeProduitList != null && this.demandeProduitList.length > 0) {

      const demande: Demande = new Demande();
      console.log('la liste de demande => ');
      console.log(this.personneDemande);
      console.log(this.mouvementDemande);


      demande.mouvement = this.mouvementDemande;
      demande.dateHeure = new Date();
      demande.personne = this.personneDemande;
      demande.valider = false;

      let newDemande: Demande = new Demande();

      this.demandeService.createDemande(demande).subscribe(
        (data: any) => {
          console.log('Enregistrement demande => ' + data);
          newDemande = data;

          const theDemandeProduit: DemandeProduit = new DemandeProduit();

          for (const dp of this.demandeProduitList) {
            theDemandeProduit.description = dp.description;
            theDemandeProduit.livrer = dp.livrer;
            theDemandeProduit.valider = dp.valider;
            theDemandeProduit.produit = dp.produit;
            theDemandeProduit.demande = data;

            console.log(theDemandeProduit);

            this.demandeProduitService.createDemandeProduit(theDemandeProduit).subscribe(
              (data1: any) => {
                console.log('Enregistrement demandeProduit => ' + data1);
              },
              (error: HttpErrorResponse) => {
                console.log('Enregistrement demandeProduit non effectuer => ' + error.message);
              }
            );

            if (this.showFieldEtat == true){
              if (dp.etat != null || dp.etat != undefined){

                const newEtatProduit: EtatProduit = new EtatProduit();
                newEtatProduit.actuel = false;
                newEtatProduit.etat = dp.etat;
                newEtatProduit.produit = dp.produit;
                newEtatProduit.dateHeure = new Date();

                this.etatProduitService.createEtatProduit(newEtatProduit).subscribe(
                  (data: any) => {
                    console.log('Enregistrement etat produit ok');
                    console.log(data);
                  },
                  (error: HttpErrorResponse) => {
                    console.log('Enregistrement de etat produit non ok');
                  });
              }
            }

          }

          this.countNew = 0;
          this.demandeProduitList = [];
          this.router.navigate(['/dashboard']).then(() => {
            window.location.reload();
          });
          this.makeDemandeForm(null);
          this.showFieldEtat = false;

        },
        (error: HttpErrorResponse) => {
          console.log('Enregistrement demande non effectué .. error => ' + error.message);
        }
      );

    }

  }


  listOfColumnHeader() {
    this.listOfColumn = [
      {
        title: 'Numero Série',
        compare: null,
        sortFn: (a: Produit, b: Produit) => a.numSerie.localeCompare(b.numSerie),
        // sortFn: (a: Produit, b: Produit) => a.numSerie - b.numSerie,
      },
      {
        title: 'Marque',
        compare: null,
        sortFn: (a: Produit, b: Produit) => a.marque.libelle.localeCompare(b.marque.libelle),
      },
      {
        title: 'Modele',
        compare: null,
        sortFn: (a: Produit, b: Produit) => a.modele.libelle.localeCompare(b.modele.libelle),
      },
      {
        title: 'Equipement',
        compare: null,
        sortFn: (a: Produit, b: Produit) => a.gamme.libelle.localeCompare(b.gamme.libelle),
      },
      {
        title: 'Demandeur',
        compare: null,
        sortFn: null,
      },
      {
        title: 'Livré',
        compare: null,
        sortFn: null,
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


  listOfColumnProduitToBeSelectedHeader() {
    this.listOfColumnProduitToBeSelected = [
      {
        title: 'Numero Série',
        compare: null,
        sortFn: (a: Produit, b: Produit) => a.numSerie.localeCompare(b.numSerie),
        // sortFn: (a: Produit, b: Produit) => a.numSerie - b.numSerie,
      },
      {
        title: 'Equipement',
        compare: null,
        sortFn: (a: Produit, b: Produit) => a.gamme.libelle.localeCompare(b.gamme.libelle),
      },
      {
        title: 'Marque',
        compare: null,
        sortFn: (a: Produit, b: Produit) => a.marque.libelle.localeCompare(b.marque.libelle),
      },
      {
        title: 'Modele',
        compare: null,
        sortFn: (a: Produit, b: Produit) => a.modele.libelle.localeCompare(b.modele.libelle),
      },
      {
        title: 'Description',
        compare: null,
        sortFn: null,
      },
    ];
  }

  equipementSelectChange(equipement: Gamme): void {
    console.log('gamme selected');
    console.log(equipement);
    this.myProduitSelected = [];
    this.myProduitListToSelected = this.produitList.filter(produit => produit.gamme.id === equipement.id &&
        produit.status === 'EN_STOCK' && ['ETAT', 'NEW'].includes(produit.etat?.code)
    );
  }
  modelSelectChange(model: Modele): void {
    console.log('model selected');
    console.log(model);
    if (this.myProduitListToSelected.length > 0) {
      this.myProduitListToSelected = this.produitList.filter(
          produit => produit.modele.id === model.id &&
          produit.status === 'EN_STOCK' && ['ETAT', 'NEW'].includes(produit.etat?.code) &&
          this.myProduitListToSelected[0].gamme.id === produit.gamme.id
      );
    }
  }


  marqueSelectChange(marque: Marque): void {
    console.log('marque selected');
    console.log(marque);
    if (this.myProduitListToSelected.length > 0) {
      this.myProduitListToSelected = this.produitList.filter(
          produit => produit.marque.id === marque.id &&
          produit.status === 'EN_STOCK' && ['ETAT', 'NEW'].includes(produit.etat?.code) &&
              this.myProduitListToSelected[0].modele.id === produit.modele.id &&
              this.myProduitListToSelected[0].gamme.id === produit.gamme.id
      );
    }
  }

  produitSelectChange(data: Produit, $target: any): void {
    console.log(data);
    console.log($target.target.checked);
    if ($target.target.checked) {
      this.myProduitSelected.push(data);
    } else {
      const index = this.myProduitSelected.indexOf(data);
      this.myProduitSelected.slice(index, 1);
    }
  }
}
