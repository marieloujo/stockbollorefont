import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DemandeProduit} from '../../models/demande-produit';
import { RequestService } from '../request/request.service';
import {DemandeRetourForm} from "../../models/demande-retour-form";

@Injectable({
  providedIn: 'root'
})
export class DemandeProduitService {

  url: string = environment.backend +'/demande-produit'

  constructor(
    private http: HttpClient, private requestService: RequestService
  ) { }

  getList(): Observable<Object> {
    return this.http.get(`${this.url}/list`, this.requestService.http_get_request());
  }

  getListForRetour(): Observable<Object> {
    return this.http.get(`${this.url}/list/retour`, this.requestService.http_get_request());
  }

  createDemandeProduit(demandeProduit: DemandeProduit): Observable<Object> {

    return this.http.post(`${this.url}/creer-demande-produit`, demandeProduit, this.requestService.http_get_request() );
  }


  createDemandeProduitRep(demandeProduit: DemandeProduit): Observable<Object> {

    return this.http.post(`${this.url}/creer-demande-produit-rep`, demandeProduit, this.requestService.http_get_request() );
  }

  
  updateDemandeProduit(demandeProduit: DemandeProduit): Observable<Object> {
    return this.http.put(`${this.url}/modifier-demande-produit`, demandeProduit, this.requestService.http_get_request());
  }

  deleteDemandeProduit(id: number): Observable<Object> {
    return this.http.delete(`${this.url}/supprimer-demande-produit/${id}`, this.requestService.http_get_request());
  }

  getListDescCreateDate(): Observable<Object> {

    return this.http.get(`${this.url}/list/desc-create-date`, this.requestService.http_get_request());

  }

  getDemandeProduitCreatedBy(id: number): Observable<Object> {
    return this.http.get(`${this.url}/demande-produit-createdBy/${id}`, this.requestService.http_get_request());
  }

  /*getDemandeProduitBetweenCreatedDate(date01: Date, date02: Date): Observable<Object> {
    return this.http.get(`${this.url}/list/between-created-date/${date01}/${date02}`, this.requestService.http_get_request());
  }*/

  getDemandeProduitBetweenCreatedDate(date01: Date, date02: Date): Observable<Object> {
    return this.http.get(`${this.url}/list/between-created-date?startDate=${date01}&endDate=${date02}`, this.requestService.http_get_request());
  }


  rejeterDemande(id: number): Observable<Object> {
    return this.http.post(`${this.url}/rejeter/` + id, {}, this.requestService.http_get_request());
  }

  validerDemande(id: number): Observable<Object> {
    return this.http.post(`${this.url}/valider/` + id, {}, this.requestService.http_get_request());
  }

  livrerDemande(id: number): Observable<Object> {
    return this.http.post(`${this.url}/livrer/` + id, {}, this.requestService.http_get_request());
  }

  retourRejeterDemande(demandeRetourForm: DemandeRetourForm): Observable<Object> {
    return this.http.post(`${this.url}/retour/create`, JSON.stringify(demandeRetourForm), this.requestService.http_get_request());
  }

  retourValiderDemande(demandeRetourForm: DemandeRetourForm): Observable<Object> {
    return this.http.post(`${this.url}/retour/valider`, JSON.stringify(demandeRetourForm), this.requestService.http_get_request());
  }

  retourCreateDemande(demandeRetourForm: DemandeRetourForm): Observable<Object> {
    return this.http.post(`${this.url}/retour/rejeter`, JSON.stringify(demandeRetourForm), this.requestService.http_get_request());
  }


}
