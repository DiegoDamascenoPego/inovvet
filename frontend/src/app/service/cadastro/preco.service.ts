import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { PrecoCalcular } from 'app/model/produto/preco';
import { Mensagem } from 'app/model/util/const-utils';


@Injectable({
   providedIn: 'root'
})
export class PrecoService {
   uri: any;

   constructor(
      private http: HttpClient
   ) {
      this.uri = 'preco'
   }

   public calcular(precoCalcular: any): Promise<any> {
      if (!precoCalcular.despesaAdicional) {
         precoCalcular.despesaAdicional = 0;
      }

      if ((precoCalcular.despesaFixa + precoCalcular.despesaVariavel + precoCalcular.margemLucro) > 100) {
         return new Promise((resolve, reject) => {
            const valor = 100 - (precoCalcular.despesaFixa + precoCalcular.despesaVariavel);
            reject({error: {titulo:Mensagem.parametroInvalido,descricao:'Margem deve ser inferior a '+ valor}});
            resolve(true);
         });
      }

      return this.http.get<any>(`${environment.api}${this.uri}/calcular`,
         {
            params: new HttpParams()
               .set('tipoCalculo', precoCalcular.tipoCalculoPreco)
               .set('tipo', precoCalcular.tipo)
               .set('custo', precoCalcular.custo)
               .set('preco', precoCalcular.preco)
               .set('despesaAdicional', precoCalcular.despesaAdicional)
               .set('despesaFixa', precoCalcular.despesaFixa)
               .set('despesaVariavel', precoCalcular.despesaVariavel)
               .set('margemLucro', precoCalcular.margemLucro)
         }
      ).toPromise();
   }

   public novo(): Promise<PrecoCalcular> {
      return this.http.get<any>(`${environment.api}${this.uri}/novo`).toPromise();
   }
}
