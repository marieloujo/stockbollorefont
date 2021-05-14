import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Profil} from '../../models/profil';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {ServiceB} from '../../models/service-b';

@Injectable({
  providedIn: 'root'
})
export class ServiceBService {

  url: string = environment.backend +'/service'

  constructor(
    private http: HttpClient
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createService(serviceB: ServiceB): Observable<Object> {
    return this.http.post(`${this.url}/creer-service`, serviceB);
  }

  updateService(serviceB: ServiceB): Observable<Object> {
    return this.http.put(`${this.url}/modifier-service`, serviceB);
  }

  deleteService(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-service/${id}`);
  }
}
