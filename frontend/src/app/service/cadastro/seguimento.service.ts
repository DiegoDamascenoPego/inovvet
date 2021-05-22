import { Seguimento } from './../../model/seguimento/seguimento';
import { Injectable } from '@angular/core';
import { Abstract } from '../abstract.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SeguimentoService extends Abstract<Seguimento> {

  constructor(
    protected http: HttpClient
  ) {
    super('seguimento', http)

  }
}
