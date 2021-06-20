import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Marque} from '../../models/marque';
import {Produit} from '../../models/produit';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Token } from 'src/app/models/token.model';
import {MagasinProduit} from "../../models/magasin-produit";

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  url: string = environment.backend +'/produit'

  constructor(
    private http: HttpClient, private Cookie: CookieService, private router: Router
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`, this.http_get_request());
  }

  createProduit(produit: Produit): Observable<Object> {
    return this.http.post(`${this.url}/creer-produit`, produit, this.http_get_request());
  }

  updateProduit(produit: Produit): Observable<Object> {
    return this.http.put(`${this.url}/modifier-produit`, produit, this.http_get_request());
  }

  deleteProduit(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-produit/${id}`, this.http_get_request());
  }

  getProduitById(id: number): Observable<Object> {
    return this.http.get(`${this.url}/get-by-id/${id}`, this.http_get_request());
  }



  http_get_request() {

    this.checkCredentials();


    const httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Access-Control-Allow-Origin':'*',
            'Authorization': 'Bearer ' + this.getAccessToken().accessToken
        })
    };

    return httpOptions;
}


checkCredentials() {
    if (!this.Cookie.check('access_token')) {
    this.logout();
    }
}


logout() {
  this.Cookie.delete('access_token', '/');
  this.Cookie.delete('user', '/');
  this.router.navigate(['/login']);
}

getAccessToken(): Token {
    let token = new Token();
    token = JSON.parse(this.Cookie.get('access_token'));

    return token;
}

    public getTextColorByStatusProduit(status: string): 'secondary' | 'warning' | 'danger' | 'success' {
        // 'secondary' | 'warning' | 'danger' | 'success'
        if (status === 'EN_STOCK'){
            return 'success';
        }
        if (['EN_ATTENTE_DE_RETOUR', 'EN_ATTENTE_DE_MISE_AU_REBUT', 'EN_ATTENTE_ENVOIE_REPARATION',
            'EN_ATTENTE_VALIDATION', 'EN_ATTENTE_LIVRAISON'].includes(status)){
            return 'warning';
        }
        if (['EN_UTILISAION', 'EN_REPARATION'].includes(status)){
            return 'secondary';
        }
        if (status === 'MISE_AU_REBUT'){
            return 'danger';
        }
    }

    public getQuantiteStockByMagasinAndProduit(produitId: number, produitsMagasin: Array<MagasinProduit>): number{
      const produitMagasin = produitsMagasin.find(proMaga => proMaga.actuel === true);
      if (![null, undefined].includes(produitMagasin)){
          return produitMagasin.quantiteStock;
      }
      return 0;
    }

}
