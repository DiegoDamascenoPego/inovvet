import { Injectable } from '@angular/core';
import {
   Router, Resolve,
   RouterStateSnapshot,
   ActivatedRouteSnapshot
} from '@angular/router';
import { SimpleEntity } from 'app/model/entity-base';
import { Observable, of } from 'rxjs';
import { UnidadeService } from './unidade.service';

@Injectable({
   providedIn: 'root'
})
export class UnidadeResolver implements Resolve<Promise<any>> {
   constructor(private service: UnidadeService) { }

   resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<any> {
      return this.service.listarTodos();
   }
}
