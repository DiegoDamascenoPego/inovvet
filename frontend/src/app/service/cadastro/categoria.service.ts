import { Injectable } from '@angular/core';
import { Abstract } from '../abstract.service';
import { EntityBase } from 'app/model/entity-base';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService  extends Abstract<EntityBase> {

  constructor(protected http: HttpClient) {
    super('categoria', http)
  }
}
