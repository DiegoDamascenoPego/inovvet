import { Injectable } from '@angular/core';
import { endpointBackend } from 'app/model/util/endpoint-config';
import { Cidade, Estado } from 'app/model/endereco/cidade';
import { Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Endereco } from 'app/model/endereco/endereco';
import { environment } from 'environments/environment';

@Injectable({
   providedIn: 'root'
})
export class EnderecoService {

   estados: Estado[];
   cidades: Cidade[];

   constructor(private http: HttpClient) {

   }

   public listarUf(): Observable<Array<Estado>> {
      return this.http.get<Array<Estado>>(`${environment.api_resource}public/cidade/uf`);
   }

   public listarCidade(uf): Observable<Array<Cidade>> {
      if (!uf) {
         return throwError('Informe o Estado');
      }
      return this.http.get<Array<Cidade>>(`${environment.api_resource}public/cidade/${uf}`);
   }

   public buscarCep(cep: String): Observable<Endereco> {
      if (!cep) {
         return throwError({ error: { tipo: 'Alerta', titulo: '', descricao: 'Informe o CEP' } });
      }
      return this.http.get<Endereco>(`${environment.api_resource}public/cidade/cep/${cep}`);
   }

}
