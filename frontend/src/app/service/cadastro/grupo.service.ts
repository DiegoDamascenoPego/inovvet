import { Injectable } from '@angular/core';
import { EntityBase } from 'app/model/entity-base';
import { Abstract } from '../abstract.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GrupoService  extends Abstract<EntityBase> {

  constructor(protected http: HttpClient) {
    super('grupo', http)
  }
}
