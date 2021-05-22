import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FunctionsUtil } from 'app/model/util/functions-util';
import { Observable } from 'rxjs';
import { Abstract } from '../abstract.service';
import { Fabricante } from './../../model/fabricante/fabricante';

@Injectable({
   providedIn: 'root'
})
export class FabricanteService extends Abstract<Fabricante> {

   constructor(protected http: HttpClient) {
      super('fabricante', http)
   }

   public pesquisarMarca(nome): Observable<any> {
      if (FunctionsUtil.isEmpty(nome)) {
         return new Observable((observer) => { });
      }
      return this.http.get<Array<any>>(`${this.urlApi}${this.uri}/marca/listar`,
         { params: new HttpParams().set('nome', nome) });
   }
}
