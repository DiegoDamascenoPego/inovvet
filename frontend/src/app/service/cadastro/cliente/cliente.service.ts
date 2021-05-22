import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cliente } from 'app/model/cliente/cliente';
import { FunctionsUtil } from 'app/model/util/functions-util';
import { throwError, Observable } from 'rxjs';
import { Mensagem } from 'app/model/util/const-utils';
import { environment } from 'environments/environment';
import { Telefone } from 'app/model/telefone/telefone';
import { Endereco } from 'app/model/endereco/endereco';
import { Abstract } from 'app/service/abstract.service';

@Injectable({
   providedIn: 'root'
})
export class ClienteService extends Abstract<Cliente> {

   constructor(protected http: HttpClient) {
      super('cliente', http)
   }


   public buscarEndereco(id: number): Observable<Endereco> {
      if (FunctionsUtil.isEmptyId(id)) {
         return throwError(Mensagem.parametroInvalido);
      }

      return this.http.get<any>(`${environment.api}${this.uri}/${id}/endereco`);
   }

   public buscarTelefones(id: number): Observable<Telefone[]> {
      if (FunctionsUtil.isEmptyId(id)) {
         return throwError(Mensagem.parametroInvalido);
      }

      return this.http.get<any>(`${environment.api}${this.uri}/${id}/telefone`);
   }

}
