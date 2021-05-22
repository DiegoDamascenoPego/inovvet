import { Injectable } from '@angular/core';
import {
   Router, Resolve,
   RouterStateSnapshot,
   ActivatedRouteSnapshot
} from '@angular/router';
import { Cliente } from 'app/model/cliente/cliente';
import { Observable, of } from 'rxjs';
import { ClienteService } from './cliente.service';

@Injectable({
   providedIn: 'root'
})
export class ClienteResolver implements Resolve<Cliente> {

   constructor(private service: ClienteService) { }

   resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Cliente> {
      const id = route.params['id']
      if (id) {
        return this.service.buscarPorId(id);
       
      } else {
         return of(null);
      }
   }
}
