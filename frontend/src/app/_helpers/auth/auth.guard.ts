import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { UsuarioLogin } from 'app/model/Sistema/usuario-login';
import { SistemaService } from 'app/service/seguranca/sistema.service';
import { UsuarioService } from 'app/service/seguranca/usuario.service';
import { MensagemService } from 'app/util/mensagem.service';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
   providedIn: 'root'
})
export class AuthGuard implements CanActivate {

   usuario: UsuarioLogin;

   constructor(
      private authService: AuthService,
      private sistemaService: SistemaService,
      private routes: Router,
      private msg: MensagemService,
      usuarioService: UsuarioService
   ) {
      usuarioService.getUsuario().subscribe(
         usuario => this.usuario = usuario
      )
   }
   canActivate(
      next: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

      if (this.authService.isLogado() && this.usuario) {
         if (this.usuario.authorities.indexOf('SISTEMA_ACESSAR') === -1) {
            this.msg.onError('Sistema não liberado para usuário', '');
            this.routes.navigate(['/login']);
            return false;
         }

         if (next.data.role && !this.sistemaService.hasAutority(next)) {
            if (next.data.redirectTo) {
               this.routes.navigate([next.data.redirectTo]);
            } else {
               if (next.url[1]) {
               } else {
                  if (state.url.includes('/cadastro/')) {
                     this.routes.navigate([state.url, 'gerenciador']);
                  } else {
                     this.routes.navigate(['/dashboard']);
                  }
               }

            }

            this.msg.onError('Recurso não liberado para usuário', '');

            return false;
         }

      } else {
         this.routes.navigate(['/login']);
         return false;
      }

      return true;

   }
}
