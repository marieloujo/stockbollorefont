import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../../services/common/behavior.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Magasin} from '../../../models/magasin';
import {MarqueService} from '../../../services/dashboard/marque.service';
import {ProduitService} from '../../../services/dashboard/produit.service';
import {Produit} from '../../../models/produit';
import { MagasinService } from 'src/app/services/dashboard/magasin.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.css']
})
export class ProduitComponent implements OnInit {

  validateProduitForm!: FormGroup;

  produitList: Produit[];
  magasinList: Magasin[];

  indexOfTab: number;

  listOfColumn: any = [];

  constructor(
    private behaviorService: BehaviorService,
    private fb: FormBuilder,
    private produitService: ProduitService,
    private magasinService: MagasinService,
  ) { }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Matériel', 'Produit']);

    this.listOfColumnHeader();
    this.listMagasin();

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
