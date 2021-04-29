import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Magasin} from '../../models/magasin';

@Injectable({
  providedIn: 'root'
})
export class MagasinService {

  url: string = environment.backend +'/magazin'

  constructor(
    private http: HttpClient
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createMagasin(magasin: Magasin): Observable<Object> {
    return this.http.post(`${this.url}/creer-magazin`, magasin);
  }

  updateMagasin(magasin: Magasin): Observable<Object> {
    return this.http.put(`${this.url}/modifier-magazin`, magasin);
  }

  deleteMagasin(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-magazin/${id}`);
  }

}
