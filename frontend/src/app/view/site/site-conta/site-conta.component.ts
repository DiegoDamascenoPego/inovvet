import {
   ChangeDetectorRef, Component,
   OnInit
} from "@angular/core";
import {
   FormBuilder, FormGroup,

   Validators
} from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { Router } from "@angular/router";
import { Endereco } from "app/model/endereco/endereco";
import { Contato } from "app/model/site/contato";
import { EnderecoService } from "app/service/cadastro/endereco.service";
import { PessoaService } from "app/service/cadastro/pessoa.service";
import { ContatoService } from "app/service/site/contato.service";
import { MensagemService } from "app/util/mensagem.service";
import { NgxSpinnerService } from "ngx-spinner";
import { tipoAviso } from "../site-aviso/site-aviso.component";
import { SiteContaTermoAceiteComponent } from "../site-conta-termo-aceite/site-conta-termo-aceite.component";

@Component({
   selector: "app-site-conta",
   templateUrl: "./site-conta.component.html",
   styleUrls: ["./site-conta.component.css"],
})
export class SiteContaComponent implements OnInit {

   form: FormGroup;
   showPainel: boolean = true;
   contato: Contato;
   tipoAviso = tipoAviso.NOVACONTA;

   constructor(
      private fb: FormBuilder,
      public enderecoService: EnderecoService,
      private pessoaService: PessoaService,
      private contatoService: ContatoService,
      private mensagem: MensagemService,
      private routes: Router,
      private spinner: NgxSpinnerService,
      private changeDetection: ChangeDetectorRef,
      public dialog: MatDialog
   ) { }

   ngOnInit(): void {
      this.setForm(new Contato());
   }

   setForm(contato: Contato) {
      this.form = this.fb.group({
         nome: [
            contato.nome,
            [
               Validators.required,
               Validators.minLength(2),
               Validators.maxLength(150),
            ],
         ],
         email: [contato.email, [Validators.required, Validators.email]],
         cpf: [contato.cpf, [Validators.required]],
         telefone: [contato.telefone, [Validators.required]],
         aceiteContrato: [contato.aceiteContrato, [Validators.required]],
         endereco: this.fb.group({
            id: [{ value: contato.endereco.id, disabled: true }],
            rua: [contato.endereco.rua, [Validators.required]],
            numero: [contato.endereco.numero, [Validators.required]],
            bairro: [contato.endereco.bairro, [Validators.required]],
            cep: [contato.endereco.cep, [Validators.required]],
            cidade: [contato.endereco.cidade, [Validators.required]],
            uf: [{ sigla: contato.endereco.cidade.uf }],
            complemento: [contato.endereco.complemento]
         }),
      });
   }

   atualizarEndereco(response: Endereco) {
      this.contato = this.form.getRawValue() as Contato;
      this.contato.endereco.cep = response.cep;
      this.contato.endereco.rua = response.rua;
      this.contato.endereco.bairro = response.bairro;
      this.contato.endereco.numero = response.numero;
      this.contato.endereco.complemento = response.complemento;
      this.contato.endereco.cidade = {
         codigo: response.cidade.codigo,
         nome: response.cidade.nome,
         uf: response.cidade.uf,
      };

      this.setForm(this.contato);
   }

   onSalvar() {
    
      this.contato = this.form.getRawValue() as Contato;

      this.spinner.show();
      this.contatoService.novaConta(this.contato).subscribe(
         (response) => {
            this.showPainel = false;            
         },
         (response) => {
            if (response.error.tipo == "Alerta") {
               this.mensagem.onAlert(
                  response.error.titulo,
                  response.error.descricao
               );
            } else {
               this.mensagem.onError(
                  response.error.titulo,
                  response.error.descricao
               );
            }
         }
      );
   }

   openDialog() {
      const dialogRef = this.dialog.open(SiteContaTermoAceiteComponent);
  
      dialogRef.afterClosed().subscribe(result => {
         let aceite = result ? true : false;
         this.contato = this.form.getRawValue() as Contato;
         this.contato.aceiteContrato = aceite;

         this.setForm(this.contato);
      });
    }

    
}
