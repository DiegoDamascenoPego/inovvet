import { Classificao } from './../../model/classificao/classificao';
import { Injectable } from '@angular/core';
import { Abstract } from '../abstract.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClassificaoService extends Abstract<Classificao> {

  constructor(protected http: HttpClient) {
    super('classificacao', http)
  }

  public listarClassificacao(): Promise<any> {
   return this.http.get<Array<any>>(`${environment.api}${this.uri}/listar/descricao`).toPromise();
 }


}
