import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../../services/common/behavior.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Magasin} from '../../../models/magasin';
import {MarqueService} from '../../../services/dashboard/marque.service';
import {ProduitService} from '../../../services/dashboard/produit.service';
import {Produit} from '../../../models/produit';
import { MagasinService } from 'src/app/services/dashboard/magasin.service';
import { HttpErrorResponse } from '@angular/common/http';
import {Gamme} from '../../../models/gamme';
import {Marque} from '../../../models/marque';
import {Modele} from '../../../models/modele';
import {GammeService} from '../../../services/dashboard/gamme.service';
import {ModeleService} from '../../../services/dashboard/modele.service';

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.css']
})
export class ProduitComponent implements OnInit {

  validateProduitForm!: FormGroup;

  produitList: Produit[];
  magasinList: Magasin[];
  gammeList: Gamme[];
  marqueList: Marque[];
  modeleList: Modele[];
  produitByMagasinIdList: Produit[] = null;

  indexOfTab: number;

  listOfColumn: any = [];
  isMagasinSelect: boolean = false;
  magasinChoice: Magasin = null;

  constructor(
    private behaviorService: BehaviorService,
    private fb: FormBuilder,
    private produitService: ProduitService,
    private magasinService: MagasinService,
    private gammeService: GammeService,
    private marqueService: MarqueService,
    private modeleService: ModeleService,
  ) { }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Matériel', 'Produit']);

    this.listOfColumnHeader();
    this.listMagasin();
    this.listGamme();
    this.listMarque();
    this.listModele();

  }

  loadMagasinProduit(){
    console.log('Le magasin');
    console.log(this.magasinChoice);
  }

  listMagasin(): void {
    this.magasinService.getList().subscribe(
      (data: Magasin[]) => {
        this.magasinList = data;
        console.log('MagasinList ==>', this.magasinList);
      },
      (error: HttpErrorResponse) => {
        console.log('error getList Magasin ==>', error.message, ' ', error.status, ' ', error.statusText);
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

  listOfColumnHeader(){
    this.listOfColumn = [
      {
        title: 'Numero Série',
        compare: null,
        priority: false
      },
      {
        title: 'Gamme',
        compare: null,
        priority: false
      },
      {
        title: 'Marque',
        compare: null,
        priority: false
      },
      {
        title: 'Modele',
        compare: null,
        priority: false
      },
      {
        title: 'Etat',
        compare: null,
        priority: false
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

}
