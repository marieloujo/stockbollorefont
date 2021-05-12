import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../services/common/behavior.service';
import {FormGroup} from '@angular/forms';
import {Produit} from '../../models/produit';

@Component({
  selector: 'app-nouvelle-demande',
  templateUrl: './nouvelle-demande.component.html',
  styleUrls: ['./nouvelle-demande.component.css']
})
export class NouvelleDemandeComponent implements OnInit {

  validateNewDemandeForm!: FormGroup;

  produitList: Produit[];

  indexOfTab: number;

  listOfColumn: any = [];

  countNew: number = 0;

  constructor(
    private behaviorService: BehaviorService,
  ) { }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Demande', 'Nouvelle demande']);
  }

  validerProduit(){
    //this.indexOfTab = 1;
    this.countNew++;
  }

  addNewProduit(){
    this.indexOfTab = 0;
  }

  countAdd (){
    this.countNew++;
  }


}
