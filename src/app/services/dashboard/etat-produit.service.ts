import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MagasinProduit} from '../../models/magasin-produit';
import {EtatProduit} from '../../models/etat-produit';

@Injectable({
  providedIn: 'root'
})
export class EtatProduitService {

  url: string = environment.backend +'/etat-produit'

  constructor(
    private http: HttpClient
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createEtatProduit(etatProduit: EtatProduit): Observable<Object> {
    return this.http.post(`${this.url}/creer-etat-produit`, etatProduit);
  }

  updateEtatProduit(etatProduit: EtatProduit): Observable<Object> {
    return this.http.put(`${this.url}/modifier-etat-produit`, etatProduit);
  }

  deleteEtatProduit(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-etat-produit/${id}`);
  }

  getEtatProduitById(id: number): Observable<Object> {
    return this.http.get(`${this.url}/get-by-id/${id}`);
  }

}
