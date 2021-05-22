import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsuarioDTO } from 'app/model/Sistema/usuario';
import { Contato } from 'app/model/site/contato';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
   providedIn: 'root'
})
export class ContatoService {

   constructor(
      protected http: HttpClient
   ) { }

   public novaConta(contato: Contato): Observable<any> {
      return this.http.post<any>(`${environment.api_accounts}conta`, contato);
   }

   public ativar(token: String): Observable<Contato> {
      return this.http.post<any>(`${environment.api_accounts}conta/ativar/` + token, null);
   }

   registrarUsuario(token: string, usuario: UsuarioDTO): Observable<any> {
      return this.http.post<any>(`${environment.api_accounts}conta/registrar/` + token, usuario);
   }


}
