import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';
import { RequestService } from 'src/app/services/request/request.service';
import { Personne } from 'src/app/models/personne';
import { TokenService } from 'src/app/services/token/token.service';
declare var $: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validateForm!: FormGroup;

  formSubmitted = false;
  loading = false;
  erreur_interne = false;
  login_echec = false;

  constructor(
      private formBuilder: FormBuilder, 
      private router: Router, 
      private requestService: RequestService, 
      private Cookie: CookieService,
      private TokenService: TokenService) { 

    this.validateForm = this.formBuilder.group({
        userName: [null, [Validators.required]],
        password: [null, [Validators.required]],
        remember: [true]
    });

  }

  ngOnInit(): void {
    
  }

  submitForm(): void { // formulaire valider

    this.formSubmitted = true;
    this.erreur_interne = false;
    this.login_echec = false;

    if (this.validateForm.valid ) {
      this.loading = true;
      let user = new Personne();

      user.username = this.validateForm.get('userName').value;
      user.password = this.validateForm.get('password').value;
      this.login(user);

    }


  }
    login(user: Personne) {
        this.requestService.http_simple_request(user, '/api/auth/login').subscribe({
            next: value => { // success
                this.erreur_interne = false;
                //console.log(value);
                this.TokenService.saveToken(value);
                /* this.Cookie.set('user', JSON.stringify(user), 1000 * environment.USER_EXPIRE_IN, '/');
                this.router.navigate(['/app/accueil']);*/
                //this.getUserConnecteByUsername(user.username);
            },
            error: err => { // erreur
                /*if (err.statusText === environment.STATUS_TEXT_UNKNOW_ERROR) { // erreur interne
                    this.erreur_interne = true;
                    this.resetLoginForm();
                } else { // login echec
                   this.login_echec = true;
                   this.loginForm.get('password').setValue('');
                }
                this.loading = false;*/
                console.log(err.status);
            },
            complete: () => { // fin de la requete
                this.loading = false;
            }
        });
    } 

    resetLoginForm() { // reset login form
        this.validateForm.reset({username: '', password: ''});
        this.formSubmitted = false;
        this.login_echec = false;
    }






}
