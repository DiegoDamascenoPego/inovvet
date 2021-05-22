import { IfStmt } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';

export interface Mensagem {
   titulo
   tituloCentralizado
   label
}

export enum tipoAviso {
   NOVOLOGIN,
   NOVACONTA
}

@Component({
   selector: 'app-site-aviso',
   templateUrl: './site-aviso.component.html',
   styleUrls: ['./site-aviso.component.css']
})
export class SiteAvisoComponent implements OnInit {

   @Input() tipoAviso = tipoAviso.NOVACONTA;

   mensagem: Mensagem;

   constructor() { }

   ngOnInit(): void {

      if (this.tipoAviso == tipoAviso.NOVACONTA) {
         this.mensagem = {
            titulo: 'Seja bem vindo à Inovvet!!!',
            tituloCentralizado: 'Agradecemos por seu interesse em utilizar nossa plataforma.',
            label: 'Para continuar você deve ativar sua conta através do link enviado para o seu  e-mail.'
         }
      } else {
         this.mensagem = {
            titulo: 'Agora falta pouco!!!',
            tituloCentralizado: 'O seu usuário foi criado em nossa plataforma.',
            label: 'Agora você deve criar a senha do usuário através do link enviado para o seu  e-mail.'
         }
      }

   }

}
