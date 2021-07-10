import {Component, OnInit} from '@angular/core';
import { Token } from '../models/token.model';
import { TokenService } from '../services/token/token.service';
import {environment} from '../../environments/environment';
import { Router } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  isCollapsed = false;
  token: Token;
  contants = environment;

  is_access: boolean;
  is_admin: boolean;
  is_validateur: boolean;
  is_gestionnaire: boolean;
  is_demandeur: boolean;
  is_auditeur: boolean;
  constructor(private tokenService: TokenService, private router: Router) {
      this.token = tokenService.getAccessToken();
  }

  ngOnInit(): void {
      this.is_access = this.canAccess();
      this.is_admin = this.isAdmin();
      this.is_validateur = this.isValidateur();
      this.is_gestionnaire = this.isGestionnaire();
      this.is_demandeur = this.isDemandeur();
      this.is_auditeur = this.isAuditeur();
  }


  canAccess(): boolean {

    return this.token.roles.indexOf(environment.ROLE_DEMANDEUR) > -1 //|| this.token.roles.indexOf(environment.ROLE_ADMIN) > -1 || this.token.roles.indexOf(environment.ROLE_VALIDATEUR) > -1;

  }


  isAdmin(): boolean {
      return this.token.roles.indexOf(environment.ROLE_ADMIN) > -1;
  }

  isValidateur(): boolean {
    return this.token.roles.indexOf(environment.ROLE_VALIDATEUR) > -1;
}

  isGestionnaire(): boolean {
  return this.token.roles.indexOf(environment.ROLE_GESTIONNAIRE) > -1;
}

  isDemandeur(): boolean {
  return this.token.roles.indexOf(environment.ROLE_DEMANDEUR) > -1;
}

  isAuditeur(): boolean {
  return this.token.roles.indexOf(environment.ROLE_AUDITEUR) > -1;
}


    logout(): void {
        this.tokenService.deleteToken();
        this.router.navigate(['/login']);
    }

}
