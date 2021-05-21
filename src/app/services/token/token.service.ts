import { Injectable } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private cookieService: CookieService) { }


    saveToken(token) { // save the token in storage
        const expireDate = new Date().getTime() + (1000 * token.expires_in);
        this.cookieService.set('access_token', token.access_token, expireDate, '/');
    }

    getAccessToken(): string {
        return this.cookieService.get('access_token');
    }

}
 