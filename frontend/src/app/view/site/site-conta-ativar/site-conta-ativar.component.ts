import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Usuario, UsuarioDTO } from 'app/model/Sistema/usuario';
import { Contato } from 'app/model/site/contato';
import { ContatoService } from 'app/service/site/contato.service';
import { MensagemService } from 'app/util/mensagem.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { tipoAviso } from '../site-aviso/site-aviso.component';

@Component({
   selector: 'app-site-conta-ativar',
   templateUrl: './site-conta-ativar.component.html',
   styleUrls: ['./site-conta-ativar.component.css']
})
export class SiteContaAtivarComponent implements OnInit {

   form: FormGroup;
   titulo = '';
   token = '';
   showPainel: boolean = true;
   usuario: UsuarioDTO = new Usuario;
   tipoAviso = tipoAviso.NOVOLOGIN;

   constructor(
      private fb: FormBuilder,
      private route: ActivatedRoute,
      private spinner: NgxSpinnerService,
      private service: ContatoService,
      private msg: MensagemService
   ) { }

   ngOnInit(): void {
      this.token = '';
      this.setForm({ nome: '', cpf: '', email: '' });

      if (this.route.snapshot.params['token']) {
         this.onCarregar(this.route.snapshot.params['token']);
      }
   }
   setForm(usuario: UsuarioDTO) {
      this.form = this.fb.group({
         nome: [usuario.nome, [Validators.required, Validators.minLength(2)]],
         email: ['', [Validators.required, Validators.email]],
         cpf: ['', [Validators.required]],
      });
   }

   onCarregar(token: string) {
      if (token != '') {
         this.spinner.show();
         this.service.ativar(token).subscribe(
            (response) => {
               this.titulo = 'Seja bem vindo à Inovvet!!!'
               this.token = token;
            },
            (error) => {
               this.titulo = 'Ops!!! Conta não encontrada'
            });
      }
   }

   onSalvar() {

      this.spinner.show();

      this.service.registrarUsuario(this.token, this.form.getRawValue() as UsuarioDTO).subscribe(
         (response) => {
            this.showPainel = false;
         },
         (response) => {
            this.msg.onMessage(
                  response.error.tipo,
                  response.error.titulo,
                  response.error.detalhe
               );
         })

   }

}
