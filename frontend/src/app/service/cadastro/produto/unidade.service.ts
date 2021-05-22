import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Abstract } from 'app/service/abstract.service';
import { SimpleEntity } from './../../../model/entity-base';

@Injectable({
  providedIn: 'root'
})
export class UnidadeService extends Abstract<SimpleEntity> {

   constructor(protected http: HttpClient) {
      super('produto/unidade', http)
   }
}
