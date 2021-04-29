import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterielRoutingModule } from './materiel-routing.module';
import {MaterielComponent} from './materiel.component';
import { MarqueComponent } from './marque/marque.component';
import { ModeleComponent } from './modele/modele.component';
import { GammeComponent } from './gamme/gamme.component';
import { EtatComponent } from './etat/etat.component';
import { ProduitComponent } from './produit/produit.component';


@NgModule({
  declarations: [MaterielComponent, MarqueComponent, ModeleComponent, GammeComponent, EtatComponent, ProduitComponent],
  imports: [
    CommonModule,
    MaterielRoutingModule
  ]
})
export class MaterielModule { }
