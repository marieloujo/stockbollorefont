import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Marque} from '../../models/marque';
import {Etat} from '../../models/etat';

@Injectable({
  providedIn: 'root'
})
export class EtatService {

  url: string = environment.backend +'/etat'

  constructor(
    private http: HttpClient
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createEtat(etat: Etat): Observable<Object> {
    return this.http.post(`${this.url}/creer-etat`, etat);
  }

  updateEtat(etat: Etat): Observable<Object> {
    return this.http.put(`${this.url}/modifier-etat`, etat);
  }

  deleteEtat(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-etat/${id}`);
  }

  getEtatByCode(code: string): Observable<Object> {
    return this.http.get(`${this.url}/get-etat-byCode/${code}`);
  }

}
