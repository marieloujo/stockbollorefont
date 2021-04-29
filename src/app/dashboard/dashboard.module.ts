import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardRoutingModule } from './dashboard-routing.module';
import {DashboardComponent} from './dashboard.component';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import {NzIconModule} from 'ng-zorro-antd/icon';
import {NzBreadCrumbModule} from 'ng-zorro-antd/breadcrumb';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {RouterModule} from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import {SharingModule} from '../sharing/sharing.module';
import { MagasinComponent } from './magasin/magasin.component';
import { NouvelleDemandeComponent } from './nouvelle-demande/nouvelle-demande.component';
import { AproposComponent } from './apropos/apropos.component';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {ReactiveFormsModule} from '@angular/forms';
import {NzSpaceModule} from 'ng-zorro-antd/space';
import {NzFormModule} from 'ng-zorro-antd/form';
import {NzRadioModule} from 'ng-zorro-antd/radio';
import {NzGridModule} from 'ng-zorro-antd/grid';
import {NzInputModule} from 'ng-zorro-antd/input';
import {NzTabsModule} from 'ng-zorro-antd/tabs';
import {NzTableModule} from 'ng-zorro-antd/table';


@NgModule({
  declarations: [DashboardComponent, AccueilComponent, MagasinComponent, NouvelleDemandeComponent, AproposComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    NzLayoutModule,
    NzIconModule,
    NzBreadCrumbModule,
    NzMenuModule,
    RouterModule,
    SharingModule,
    NzButtonModule,
    ReactiveFormsModule,
    NzSpaceModule,
    NzFormModule,
    NzRadioModule,
    NzGridModule,
    NzInputModule,
    NzTabsModule,
    NzTableModule,
  ]
})
export class DashboardModule { }
