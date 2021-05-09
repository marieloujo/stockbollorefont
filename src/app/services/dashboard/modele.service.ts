import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Gamme} from '../../models/gamme';
import {Modele} from '../../models/modele';

@Injectable({
  providedIn: 'root'
})
export class ModeleService {

  url: string = environment.backend +'/modele'

  constructor(
    private http: HttpClient
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createModele(modele: Modele): Observable<Object> {
    return this.http.post(`${this.url}/creer-modele`, modele);
  }

  updateModele(modele: Modele): Observable<Object> {
    return this.http.put(`${this.url}/modifier-modele`, modele);
  }

  deleteModele(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-modele/${id}`);
  }

}
