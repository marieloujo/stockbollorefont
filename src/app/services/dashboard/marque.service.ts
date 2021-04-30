import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Gamme} from '../../models/gamme';
import {Marque} from '../../models/marque';

@Injectable({
  providedIn: 'root'
})
export class MarqueService {

  url: string = environment.backend +'/marque'

  constructor(
    private http: HttpClient
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createMarque(marque: Marque): Observable<Object> {
    return this.http.post(`${this.url}/creer-marque`, marque);
  }

  updateMarque(marque: Marque): Observable<Object> {
    return this.http.put(`${this.url}/modifier-marque`, marque);
  }

  deleteMarque(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-marque/${id}`);
  }

}
