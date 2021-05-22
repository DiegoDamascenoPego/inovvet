import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Arquivo } from 'app/model/arquivo/arquivo';
import { environment } from 'environments/environment';
import { Abstract } from '../abstract.service';
import { ArquivoView } from './../../model/arquivo/arquivo';

@Injectable({
   providedIn: 'root'
})
export class ArquivoService extends Abstract<Arquivo> {

   constructor(protected http: HttpClient) {
      super('arquivo', http)
   }

   private salvarArquivo(arquivo: Arquivo, formData: FormData): Promise<any> {
   
      return this.http.post<any>(`${environment.api}${this.uri}`, formData,
         { params: new HttpParams()
            .set('modulo', arquivo.modulo.toString())
            .set('tipoArquivo', arquivo.tipo.toString())
            .set('referencia', arquivo.referencia.toString())
            .set('referenciaId', arquivo.referenciaId.toString()) }).toPromise();
   }

   public salvar(arquivo: ArquivoView): Promise<any> {
      const formData = new FormData();
      formData.append('arquivo', arquivo.file, arquivo.file.name);
      return this.salvarArquivo(arquivo, formData);

   }

   public removerImagem(id: number): Promise<any> {
      return this.remover(id);
   }

}
