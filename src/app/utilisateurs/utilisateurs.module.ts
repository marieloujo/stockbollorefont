import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UtilisateursRoutingModule } from './utilisateurs-routing.module';
import { UtilisateursComponent } from './utilisateurs.component';
import { ProfilComponent } from './profil/profil.component';
import { ServiceComponent } from './service/service.component';
import { UserRunComponent } from './user-run/user-run.component';


@NgModule({
  declarations: [UtilisateursComponent, ProfilComponent, ServiceComponent, UserRunComponent],
  imports: [
    CommonModule,
    UtilisateursRoutingModule
  ]
})
export class UtilisateursModule { }
