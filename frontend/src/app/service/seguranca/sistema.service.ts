import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { UsuarioLogin } from './../../model/Sistema/usuario-login';
import { UsuarioService } from './usuario.service';

@Injectable({
   providedIn: 'root'
})

export class SistemaService {

   usuario: UsuarioLogin;

   constructor(
      private usuarioService: UsuarioService) {

         usuarioService.getUsuario()
         .subscribe(usuario => this.usuario = usuario);
       }

  

   carregarSistema(): Observable<any> {
      return this.usuarioService.carregarUsuarioLogin();
   }

   hasAutority(snapshot: ActivatedRouteSnapshot): boolean {
      return this.usuario.authorities.indexOf(snapshot.data.role) > -1;
   }

   hasAutorityRole(role: string): boolean {
      return this.usuario.authorities.indexOf(role) > -1;
   }

   hasAutorityRemove(snapshot: ActivatedRouteSnapshot): boolean {
      return this.usuario.authorities.indexOf(snapshot.data.role.split(['_'], [1]) + '_REMOVER') > -1;
   }

}
