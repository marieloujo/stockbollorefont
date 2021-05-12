import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profil } from 'src/app/models/profil';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfilService {

  url: string = environment.backend +'/profil'

  constructor(
    private http: HttpClient
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`);
  }

  createProfil(profil: Profil): Observable<Object> {
    return this.http.post(`${this.url}/creer-profil`, profil);
  }

  updateProfil(profil: Profil): Observable<Object> {
    return this.http.put(`${this.url}/modifier-profil`, profil);
  }

  deleteProfil(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-profil/${id}`);
  }

}
