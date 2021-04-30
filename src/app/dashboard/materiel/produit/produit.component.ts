import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../../services/common/behavior.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Magasin} from '../../../models/magasin';
import {MarqueService} from '../../../services/dashboard/marque.service';
import {ProduitService} from '../../../services/dashboard/produit.service';
import {Produit} from '../../../models/produit';

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.css']
})
export class ProduitComponent implements OnInit {

  validateMagasinForm!: FormGroup;

  produitList: Produit[];

  indexOfTab: number;

  listOfColumn: any = [];

  constructor(
    private behaviorService: BehaviorService,
    private fb: FormBuilder,
    private produitService: ProduitService
  ) { }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Matériel', 'Produit']);



  }

}
