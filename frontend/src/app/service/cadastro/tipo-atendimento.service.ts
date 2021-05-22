import { Injectable } from '@angular/core';
import { TipoAtendimento } from 'app/model/atendimento/tipo-atendimento';
import { Abstract } from '../abstract.service';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class TipoAtendimentoService extends Abstract<TipoAtendimento> {

  constructor(protected http: HttpClient) {
    super('tipoatendimento', http)
  }
}
