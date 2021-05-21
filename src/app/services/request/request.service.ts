import { Injectable } from '@angular/core';
import {Router} from '@angular/router';import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs/index';
import {CookieService} from 'ngx-cookie-service';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Personne } from 'src/app/models/personne';


@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private router: Router, private http: HttpClient, private Cookie: CookieService) { }

    http_simple_request(personne: Personne, url: String): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Access-Control-Allow-Origin':'*',
            })
          };
        return this.http.post(environment.backend + url, personne, httpOptions);
    }

    

}
