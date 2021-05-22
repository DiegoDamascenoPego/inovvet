import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Produto } from 'app/model/produto/produto';
import { Abstract } from '../../abstract.service';

@Injectable({
   providedIn: 'root'
})
export class ProdutoService extends Abstract<Produto> {

   constructor(protected http: HttpClient) {
      super('produto', http)
   }




}
