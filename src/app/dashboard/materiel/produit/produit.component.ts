import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../../services/common/behavior.service';

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.css']
})
export class ProduitComponent implements OnInit {

  constructor(
    private behaviorService: BehaviorService,
  ) { }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Matériel', 'Produit']);
  }

}
