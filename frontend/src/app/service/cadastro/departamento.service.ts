import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Departamento } from 'app/model/classificao/departamento';
import { Abstract } from '../abstract.service';

@Injectable({
  providedIn: 'root'
})
export class DepartamentoService  extends Abstract<Departamento> {

  constructor(protected http: HttpClient) {
    super('departamento', http)
  }
}
