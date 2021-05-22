import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { UsuarioLogin } from './../../model/Sistema/usuario-login';
import { UsuarioResource } from './../../rest/Sistema/usuario-resource';
import { Injectable } from '@angular/core';
import { promise } from 'protractor';
import { AuthService } from 'app/_helpers/auth/auth.service';
import { UsuarioDTO, UsuarioSimplesDTO } from 'app/model/Sistema/usuario';
import { TokenService } from 'app/_helpers/token/token.service';
import { tap, switchMap  } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
   providedIn: 'root'
})
export class UsuarioService {

   private usuarioLogin: UsuarioLogin;

   private userToken: string;

   private userSubject = new BehaviorSubject<UsuarioLogin>(null);

   constructor(
      private tokenService: TokenService,
      private usuarioResource: UsuarioResource,
      private router: Router,
      private authService: AuthService) {

      this.userToken = tokenService.carregarUserToken();
   }

   carregarUsuarioLogin(): Observable<UsuarioLogin> {

      return this.usuarioResource
         .carregar(this.tokenService.carregarUserToken())
            .pipe(tap(response =>{
               this.userSubject.next(response)
            }));
   }

   carregarUsuarioConta(token: string): Observable<UsuarioSimplesDTO> {
      return this.usuarioResource.carregarUsuarioConta(token);
   }

   registrarSenha(token: string, senha: string): Observable<any> {
      return this.usuarioResource.registrarSenha(token, senha);
   }

   getUsuario(){
      return this.userSubject;
   }

   logout(){
        
      this.authService.logout().subscribe( ()=> {
         this.tokenService.removerToken();
         this.userSubject.next(null);
         this.router.navigate(['/login']);
      }); 

   }


}
