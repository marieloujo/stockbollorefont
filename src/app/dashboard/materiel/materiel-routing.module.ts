import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DashboardComponent} from '../dashboard.component';
import {AccueilComponent} from '../accueil/accueil.component';
import {MagasinComponent} from '../magasin/magasin.component';
import {NouvelleDemandeComponent} from '../nouvelle-demande/nouvelle-demande.component';
import {AproposComponent} from '../apropos/apropos.component';
import {MaterielComponent} from './materiel.component';
import {EtatComponent} from './etat/etat.component';
import {GammeComponent} from './gamme/gamme.component';
import {MarqueComponent} from './marque/marque.component';
import {ModeleComponent} from './modele/modele.component';
import {ProduitComponent} from './produit/produit.component';

const routes: Routes = [
  {
    path: '', component: MaterielComponent,
    children: [
      {
        path: 'etat',
        component: EtatComponent,
      },
      {
        path: 'gamme',
        component: GammeComponent,
      },
      {
        path: 'marque',
        component: MarqueComponent,
      },
      {
        path: 'modele',
        component: ModeleComponent,
      },
      {
        path: 'produit',
        component: ProduitComponent,
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MaterielRoutingModule { }
