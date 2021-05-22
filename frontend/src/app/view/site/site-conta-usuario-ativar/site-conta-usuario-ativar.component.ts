import { RepositionScrollStrategy } from '@angular/cdk/overlay';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioSimplesDTO } from 'app/model/Sistema/usuario';
import { UsuarioService } from 'app/service/seguranca/usuario.service';
import { ContatoService } from 'app/service/site/contato.service';
import { MensagemService } from 'app/util/mensagem.service';
import { passwordValidar } from 'app/_helpers/validators/passwordvalidar.validators';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
   selector: 'app-site-conta-usuario-ativar',
   templateUrl: './site-conta-usuario-ativar.component.html',
})
export class SiteContaUsuarioAtivarComponent implements OnInit {

   form: FormGroup;
   usuario: UsuarioSimplesDTO = { nome: '' };
   viewPs = false;

   labelTitulo = 'Nova Senha'

   constructor(
      private fb: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private spinner: NgxSpinnerService,
      private service: UsuarioService,
      private msg: MensagemService
   ) { }

   ngOnInit(): void {
      if (this.route.snapshot.params['token']) {
         this.onCarregar(this.route.snapshot.params['token']);
         this.labelTitulo = 'Agora é só criar as credenciais de acesso!!!';
         
      }

      this.form = this.fb.group({
         senha: ['', [Validators.required, Validators.minLength(6)]],
         senhaConfirmar: ['', [Validators.required, Validators.minLength(6)]],
      },
      {validators: passwordValidar});
   }

   async onCarregar(token: string) {

      if (token != '') {
         this.spinner.show();
         await this.service.carregarUsuarioConta(token).subscribe(
            (response) => {
               this.usuario = response;
            },
            (error) => {
               console.log(error);
            });
      }
   }

   onSalvar() {
      this.service.registrarSenha(this.usuario.token, this.form.get('senha').value).subscribe(
         (response) => {
            this.msg.onSucess('','Usuário Ativado com Sucesso');
            this.router.navigateByUrl('/login')
         }
      )
   }

}
