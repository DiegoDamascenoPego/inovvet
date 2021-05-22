import { SistemaService } from './../../service/seguranca/sistema.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MensagemService } from 'app/util/mensagem.service';
import { AuthService } from 'app/_helpers/auth/auth.service';
import { debug } from 'console';

@Component({
   selector: 'app-login-form',
   templateUrl: './login-form.component.html',
   styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {

   constructor(
      private router: Router,
      private auth: AuthService,
      private sistemaService: SistemaService,
      private mensagemService: MensagemService
   ) { }


   login(username: string, password: string) {
      this.auth.login(username, password).subscribe((response) => {
         if (this.auth.isLogado()) {
            this.sistemaService.carregarSistema().subscribe((info) => {
               if (info.lojas[0]) {
                  this.router.navigate(['/dashboard']);
               } else {
                  this.router.navigate(['/cadastro/loja']);
               }
            },
               (error) => {
                  this.mensagemService.onError('', 'Falha ao realizar login')
               });
         } else {
            this.mensagemService.onError('', 'Falha ao realizar login')
         }
      },
         (error) => {
            this.mensagemService.onError('', 'Falha ao realizar login')
         });
   }

}
