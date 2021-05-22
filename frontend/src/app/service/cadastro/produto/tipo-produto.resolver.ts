import { Injectable } from '@angular/core';
import {
   Router, Resolve,
   RouterStateSnapshot,
   ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { TipoProdService } from './tipo-prod.service';

@Injectable({
   providedIn: 'root'
})
export class TipoProdutoResolver implements Resolve<Promise<any>> {

   constructor(private service: TipoProdService) { }

   resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<any> {
      return this.service.listarTodos();
   }
}
