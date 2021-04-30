import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Marque} from '../../models/marque';
import {Produit} from '../../models/produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  url: string = environment.backend +'/produit'

  constructor(
    private http: HttpClient
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createMarque(produit: Produit): Observable<Object> {
    return this.http.post(`${this.url}/creer-produit`, produit);
  }

  updateMarque(produit: Produit): Observable<Object> {
    return this.http.put(`${this.url}/modifier-produit`, produit);
  }

  deleteMarque(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-marque/${id}`);
  }

}
