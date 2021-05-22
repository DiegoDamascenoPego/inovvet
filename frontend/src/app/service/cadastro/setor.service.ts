import { Setor } from '../../model/setor/setor';
import { Abstract } from 'app/service/abstract.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SetorService extends Abstract<Setor> {

  constructor(
    protected http: HttpClient
  ) {
    super('setor', http)

  }
}
