import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Profil} from '../../models/profil';
import {MagasinProduit} from '../../models/magasin-produit';

@Injectable({
  providedIn: 'root'
})
export class MagasinProduitService {

  url: string = environment.backend +'/magazin-produit'

  constructor(
    private http: HttpClient
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createMagasinProduit(magasinProduit: MagasinProduit): Observable<Object> {
    return this.http.post(`${this.url}/creer-magazin-produit`, magasinProduit);
  }

  updateMagasinProduit(magasinProduit: MagasinProduit): Observable<Object> {
    return this.http.put(`${this.url}/modifier-magazin-produit`, magasinProduit);
  }

  deleteMagasinProduit(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-magazin-produit/${id}`);
  }

}
