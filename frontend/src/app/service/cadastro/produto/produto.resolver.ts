import { Injectable } from '@angular/core';
import {
   Router, Resolve,
   RouterStateSnapshot,
   ActivatedRouteSnapshot
} from '@angular/router';
import { Produto } from 'app/model/produto/produto';
import { Observable, of } from 'rxjs';
import { ProdutoService } from './produto.service';

@Injectable({
   providedIn: 'root'
})
export class ProdutoResolver implements Resolve<Produto> {

   constructor(private service: ProdutoService) { }

   resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Produto> {

      if (route.params['id']) {
         return this.service.buscarPorId(route.params['id']);
      } else {
         return of(null);
      }
   }
}
