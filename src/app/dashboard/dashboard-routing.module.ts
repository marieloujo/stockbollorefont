import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DashboardComponent} from './dashboard.component';
import {AccueilComponent} from './accueil/accueil.component';

const routes: Routes = [
  {
    path: '', component: DashboardComponent,
    children: [
      {
        path: '',
        component: AccueilComponent,
      },
      /*{
        path: 'rc',
        loadChildren: () => import('./rc/rc.module').then(value => value.RcModule)
      },
      {
        path: 'sie',
        loadChildren: () => import('./sie/sie.module').then(value => value.SieModule)
      },
      {
        path: 'sig',
        loadChildren: () => import('./sig/sig.module').then(value => value.SigModule)
      },*/
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
