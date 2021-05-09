import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ConnexionRoutingModule } from './connexion-routing.module';
import {ConnexionComponent} from './connexion.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';


@NgModule({
  declarations: [ConnexionComponent, LoginComponent, RegisterComponent, ForgotPasswordComponent],
  imports: [
    CommonModule,
    ConnexionRoutingModule
  ]
})
export class ConnexionModule { }
