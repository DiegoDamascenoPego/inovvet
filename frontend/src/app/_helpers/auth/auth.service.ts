
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Loja } from 'app/model/Sistema/loja';
import { Usuario } from 'app/model/Sistema/usuario';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { TokenService } from '../token/token.service';

@Injectable({
   providedIn: 'root'
})
export class AuthService {

   oauth = environment.oauthApi;
   acess_token: string;

   constructor(
      private http: HttpClient,
      private tokenService: TokenService
   ) { }

   login(username: string, password: string): Observable<any> {

      let headers = new HttpHeaders()
         .append('Content-Type', 'application/x-www-form-urlencoded')
         .append('Authorization', 'Basic YXBwLm1hbmFnZXI6cGFp');

      const body = `username=${username}&password=${password}&grant_type=password`;

      return this.http.post(this.oauth + 'token', body, { headers: headers })
         .pipe(tap(response => {
            this.tokenService.armazenarToken(
               response.access_token,
               response.utoken,
               response.ctoken);
         }));
   }

   loginAccounts(): Observable<any> {
      const headers = new HttpHeaders()
         .append('Content-Type', 'application/x-www-form-urlencoded')
         .append('Authorization', 'Basic YXBwLm1hbmFnZXI6cGFp');

      const body = `grant_type=client_credentials`;

      return this.http.post(this.oauth, body, { headers });
   }

   armazenarLoja(loja: Loja) {
      localStorage.setItem('nomeFantasia', loja.nomeFantasia);
      localStorage.setItem('token', loja.token);
   }

   armazenarUsuario(usuario: Usuario) {
      localStorage.setItem('nome', usuario.nome);
      localStorage.setItem('token', usuario.token);
      localStorage.setItem('authorities', usuario.authorities.toString());
   }



   isLogado(): boolean {
      return !!this.tokenService.carregarToken() &&
         !!this.tokenService.carregarUserToken() &&
         !!this.tokenService.carregarContaToken();

   }

   logout() {
      let headers = new HttpHeaders()
         .append('Content-Type', 'application/x-www-form-urlencoded')
         .append('Authorization', 'Bearer ' + this.tokenService.carregarToken())

      return this.http.post(this.oauth + 'logout', null, { headers: headers });
   }

}

