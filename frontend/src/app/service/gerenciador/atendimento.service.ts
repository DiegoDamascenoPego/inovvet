import { Injectable } from '@angular/core';
import { Atendimento } from 'app/model/atendimento/atendimento';
import { Abstract } from '../abstract.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { FunctionsUtil } from 'app/model/util/functions-util';
import { Mensagem } from 'app/model/util/const-utils';
import { environment } from 'environments/environment';

@Injectable({
   providedIn: 'root'
})
export class AtendimentoService extends Abstract<Atendimento> {

   constructor(protected http: HttpClient) {
      super('atendimento', http)
   }


   public listarVagas(idAtendimento: number, data: Date): Observable<any> {
      if (FunctionsUtil.isEmptyId(idAtendimento)) {
         return throwError(Mensagem.parametroInvalido);
      }
      return this.http.get<any>(`${environment.api}${this.uri}/${idAtendimento}/vagas`,
         { params: new HttpParams().set('data', FunctionsUtil.dateToFormat(data.toString())) });
   }

   public relatorioAtendimento(): Observable<any> {

      return this.http.get<Blob>(`${environment.api}${this.uri}/relatorio`, { responseType: 'blob' as 'json' });
   }


   public finalizar(idAtendimento: number): Observable<void> {
      if (FunctionsUtil.isEmptyId(idAtendimento)) {
         return throwError(Mensagem.parametroInvalido);
      }
      return this.http.post<void>(`${environment.api}${this.uri}/${idAtendimento}/finalizar`,undefined);
   }
}
