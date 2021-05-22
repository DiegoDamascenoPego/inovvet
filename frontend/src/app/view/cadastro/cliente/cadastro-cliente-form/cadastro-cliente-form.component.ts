// tslint:disable-next-line:max-line-length
import { Component, EventEmitter, Injector, Input, OnChanges, OnInit, Output, SimpleChanges, TemplateRef, ViewChild } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { OverlayRef } from 'app/components/overlay/overlay-ref';
import { OverlayService } from 'app/components/overlay/service/overlay.service';
import { Genero } from 'app/model/cliente/genero.enum';
import { ClienteService } from 'app/service/cadastro/cliente/cliente.service';
import { FormBase } from 'app/view/compartilhado/base/form-base';
import { fade } from 'app/_helpers/animations/fade';
import { EnumValues } from 'enum-values';
import * as moment from 'moment';
import { Cliente } from './../../../../model/cliente/cliente';
import { Endereco } from './../../../../model/endereco/endereco';
import { Telefone } from './../../../../model/telefone/telefone';
import { FunctionsUtil } from './../../../../model/util/functions-util';

@Component({
   selector: 'app-cadastro-cliente-form',
   templateUrl: './cadastro-cliente-form.component.html',
   animations: [fade]
})
export class CadastroClienteFormComponent extends FormBase<Cliente> implements OnChanges {

   @ViewChild('overlay') public overlay: TemplateRef<any>;
   public overlayRef: OverlayRef;

   @Output() closeCadastro = new EventEmitter;
   @Input() clienteSelecionado: Cliente;
   form: FormGroup;

   telefones: Telefone[] = new Array();

   generos = EnumValues.getNamesAndValues(Genero);


   functionsUtil: FunctionsUtil;
   showFormPets = false;

   constructor(
      protected service: ClienteService,
      protected injector: Injector,
      private overlayService: OverlayService
   ) {
      super(service, injector);
      this.tabCount = 3;
   }

   ngOnChanges(changes: SimpleChanges): void {
      if (changes.clienteSelecionado.currentValue) {
         this.onCarregar(changes.clienteSelecionado.currentValue.id);
      }
   }

   setForm(entity: Cliente) {
      this.form = this.fb.group({
         id: [{ value: entity.id, disabled: true }],
         nome: [entity.nome, [Validators.required, Validators.minLength(2), Validators.maxLength(150)]],
         email: [entity.email, [Validators.email]],
         observacao: [entity.observacao, [Validators.maxLength(255)]],
         tipo: [entity.tipo],
         cpf: [entity.cpf, [Validators.required]],
         sexo: [entity.sexo, [Validators.required]],
         dataNascimento: [entity.dataNascimento, [Validators.required]],
         ativo: [entity.ativo],
         endereco: this.fb.group({
            id: [{ value: entity.endereco.id, disabled: true }],
            tipo:[entity.endereco.tipo],
            rua: [entity.endereco.rua, [Validators.minLength(2), Validators.maxLength(150)]],
            numero: [entity.endereco.numero],
            bairro: [entity.endereco.bairro],
            cep: [entity.endereco.cep],
            cidade: [entity.endereco.cidade],
            uf: [{ sigla: entity.endereco.cidade.uf }],
            complemento: [entity.endereco.complemento],
         })
      });
   }
   newEntity(): Cliente {
      return new Cliente();
   }


   setFormToEntity() {
      this.entity = this.form.getRawValue() as Cliente;
      this.entity.telefones = this.telefones;
      this.entity.dataNascimento = moment(this.entity.dataNascimento).format('DD/MM/YYYY');
   }

   setEntity(response: any) {
      this.entity = response;
      this.telefones = this.entity.telefones;
      this.entity.dataNascimento = FunctionsUtil.dateToString(this.entity.dataNascimento);

      if (!this.entity.endereco) {
         this.entity.endereco = new Endereco();
      }

      this.setForm(this.entity);
   }


   onUpdateTelefones(event) {
      this.telefones = event;
   }

   onShowCadastroPet() {
      this.overlayRef = this.overlayService.open({
         templateRef: this.overlay,
         title: 'Cadastro de Pets'
       });
   }


   onVoltar() {
      this.closeCadastro.emit();
   }

   atualizarEndereco(response: Endereco) {
      this.entity = this.form.getRawValue() as Cliente;
      this.entity.endereco.cep = response.cep;
      this.entity.endereco.rua = response.rua;
      this.entity.endereco.bairro = response.bairro;
      this.entity.endereco.numero = response.numero;
      this.entity.endereco.complemento = response.complemento;
      this.entity.endereco.cidade = { codigo: response.cidade.codigo, nome: response.cidade.nome, uf: response.cidade.uf };
      this.setForm(this.entity);
     
   }

}
