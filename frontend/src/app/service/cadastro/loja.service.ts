import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Loja, LojaCadastro } from 'app/model/Sistema/loja';
import { UsuarioLogin } from 'app/model/Sistema/usuario-login';
import { BehaviorSubject } from 'rxjs';
import { Abstract } from '../abstract.service';
import { UsuarioService } from '../seguranca/usuario.service';

@Injectable({
   providedIn: 'root'
})
export class LojaService extends Abstract<LojaCadastro> {

   private lojaSubject = new BehaviorSubject<Loja>(null);
   private lojasSubject = new BehaviorSubject<Loja[]>(null);

   constructor(protected http: HttpClient,
      usuarioService: UsuarioService) {
      super('loja', http);

      usuarioService
         .getUsuario()
         .subscribe(usuario => this.definirLoja(usuario)
         );
   }

   definirLoja(usuario: UsuarioLogin) {
      if (usuario) {
         const loja = new Loja();

         if (usuario.lojas[0]) {
            loja.nomeFantasia = usuario.lojas[0].nomeFantasia;
            loja.token = usuario.lojas[0].token;
         }

         this.lojaSubject.next(loja);
         this.lojasSubject.next(usuario.lojas)
      }

   }

   getLoja() {
      return this.lojaSubject;
   }

   getLojas() {
      return this.lojasSubject;
   }
}
