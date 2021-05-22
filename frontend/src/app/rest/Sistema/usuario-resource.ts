import { UsuarioLogin } from './../../model/Sistema/usuario-login';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'environments/environment';
import { UsuarioDTO, UsuarioSimplesDTO } from 'app/model/Sistema/usuario';

@Injectable({
   providedIn: 'root'
})

export class UsuarioResource {

   constructor(private http: HttpClient) { }

   carregar(token: string): Observable<UsuarioLogin> {
      return this.http.get<UsuarioLogin>(`${environment.api}usuario`, { params: new HttpParams().set('token', token) });
   }

   carregarUsuarioConta(token: string): Observable<UsuarioSimplesDTO> {
      return this.http.get<UsuarioLogin>(`${environment.api_accounts}usuario/carregar/` + token);
   }

   registrarSenha(token: string, senha: string): Observable<any> {
      return this.http.post<any>(`${environment.api_accounts}usuario/ativar/` + token, senha);
   }
}
