import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Magasin} from '../../models/magasin';
import { RequestService } from '../request/request.service';

@Injectable({
  providedIn: 'root'
})
export class PersonneService {

  url: string = environment.backend +'/personne'

  constructor(
    private http: HttpClient, private requestService: RequestService
  ) { }




  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createPersonne(magasin: Magasin): Observable<Object> {
    return this.http.post(`${this.url}/creer-personne`, magasin);
  }

  updatePersonne(magasin: Magasin): Observable<Object> {
    return this.http.put(`${this.url}/modifier-personne`, magasin);
  }

  deletePersonne(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-personne/${id}`);
  }

}
