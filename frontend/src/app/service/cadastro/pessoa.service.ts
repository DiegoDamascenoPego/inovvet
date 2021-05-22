import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { Pessoa } from 'app/model/pessoa/pessoa';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor(private http: HttpClient) { }

   public consultar(cnpj: string): Observable<Pessoa> {
    if (cnpj) {
      return this.http.get<Pessoa>(`${environment.api_resource}public/parceiro/consulta/${cnpj}`);
    }

    return throwError('Informe o CNPJ');

  }
}
