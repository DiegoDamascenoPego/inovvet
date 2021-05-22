import { Component, Injector, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Validators } from '@angular/forms';
import { OverlayRef } from 'app/components/overlay/overlay-ref';
import { OverlayService } from 'app/components/overlay/service/overlay.service';
import { Atendimento } from 'app/model/atendimento/atendimento';
import { EnumStatusAtendimento } from 'app/model/atendimento/enum-status-atendimento.enum';
import { TipoAtendimento } from 'app/model/atendimento/tipo-atendimento';
import { Vaga } from 'app/model/atendimento/vaga';
import { Endereco } from 'app/model/endereco/endereco';
import { EntityBase } from 'app/model/entity-base';
import { Telefone } from 'app/model/telefone/telefone';
import { AnimalService } from 'app/service/cadastro/animal.service';
import { ClienteService } from 'app/service/cadastro/cliente/cliente.service';
import { EnderecoService } from 'app/service/cadastro/endereco.service';
import { TipoAtendimentoService } from 'app/service/cadastro/tipo-atendimento.service';
import { AtendimentoService } from 'app/service/gerenciador/atendimento.service';
import { FormBase } from 'app/view/compartilhado/base/form-base';
import { fade } from 'app/_helpers/animations/fade';
import { EnumValues } from 'enum-values';

@Component({
   selector: 'app-atendimento-form',
   templateUrl: './atendimento-form.component.html',
   styleUrls: ['./atendimento-form.component.css'],
   animations: [fade]
})
export class AtendimentoFormComponent extends FormBase<Atendimento> implements OnInit {

   @ViewChild('overlay') public clienteFormOverlay: TemplateRef<any>;

   status = EnumValues.getNamesAndValues(EnumStatusAtendimento);
   tipoAtendimentos: TipoAtendimento[] = [];
   vagas: Vaga[] = [];
   animais: EntityBase[] = [];
   cliente: EntityBase;

   public overlayRef: OverlayRef;

   constructor(protected service: AtendimentoService,
      protected injector: Injector,
      private tipoAtendimentoService: TipoAtendimentoService,
      public clienteService: ClienteService,
      private animalService: AnimalService,
      public enderecoService: EnderecoService,
      private overlayService: OverlayService
   ) {
      super(service, injector);
      this.tabCount = 2;
   }


   ngOnInit(): void {
      this.tipoAtendimentoService.listarTodos().then((response) => {
         this.tipoAtendimentos = response;
         this.entity.tipoAtendimento = response[0];
         this.setForm(this.entity);
         this.carregarVagas(this.entity.tipoAtendimento.id, this.entity.data);
      });

      super.ngOnInit();
   }

   onChangeVagas() {
      this.setFormToEntity();
      this.carregarVagas(this.entity.tipoAtendimento.id, this.entity.data);
   }

   carregarVagas(atendimentoId: number, data: Date) {
      this.listarvagas(atendimentoId, data);
   }

   listarvagas(atendimentoId: number, data: Date) {
      this.vagas = [];
      this.service.listarVagas(atendimentoId, data).subscribe((response) => {
         this.vagas = response;
      });
   }

   setForm(entity: Atendimento) {
      this.form = this.fb.group({
         id: [{ value: entity.id, disabled: true }],
         data: [entity.data, [Validators.required]],
         vaga: [entity.vaga, [Validators.required]],
         tipoAtendimento: [entity.tipoAtendimento],
         animal: [entity.animal, [Validators.required]],
         status: [entity.status, [Validators.required]],
         observacao: [entity.observacao],
         telefones: [entity.telefones],
         endereco: this.fb.group({
            id: [{value: entity.endereco.id, disabled: true }],
            rua: [entity.endereco.rua, [Validators.required]],
            numero: [entity.endereco.numero, [Validators.required]],
            bairro: [entity.endereco.bairro, [Validators.required]],
            cep: [entity.endereco.cep, [Validators.required]],
            complemento: [entity.endereco.complemento],
            tipo: [entity.endereco.tipo],
            cidade: [entity.endereco.cidade, [Validators.required]],
            uf: [{ sigla: entity.endereco.cidade.uf }],
         })
      });
   }
   newEntity(): Atendimento {
      this.cliente = undefined;
      return new Atendimento();
   }

   setFormToEntity() {
      this.entity = this.form.getRawValue() as Atendimento;
      this.entity.cliente = this.cliente;
   }

   setEntity(response: any) {

      this.entity = response;
      this.entity.data = new Date(response.data + " 00:00:01");

      this.cliente = this.entity.cliente;

      this.listarAnimais();
      this.carregarEndereco();
      this.carregarTelefones();
      this.setForm(response);
      this.carregarVagas(response.tipoAtendimento.id, this.form.get('data').value);
   }

   onAdicionarCliente(event) {
      if (event) {
         this.cliente = event;
      } else {
         this.cliente = undefined;
      }

      this.listarAnimais();
      this.carregarEndereco();
      this.carregarTelefones();
   }

   listarAnimais() {

      if (this.cliente) {
         this.animalService.buscarPorClienteId(this.cliente).subscribe((response) => {
            this.animais = response;
         });
      } else {
         this.animais = [];
      }
   }

   carregarEndereco() {
      if (this.cliente) {
         this.clienteService.buscarEndereco(this.cliente.id).subscribe((response) => {
            this.atualizarEndereco(response);
         });
      } else {
         this.atualizarEndereco(new Endereco());
      }
   }

   carregarTelefones() {
      if (this.cliente) {
         this.clienteService.buscarTelefones(this.cliente.id).subscribe((response) => {
            this.atualizarTelefone(response);
         });
      } else {
         this.atualizarTelefone([]);
      }
   }

   atualizarEndereco(response: Endereco) {
      this.entity = this.form.getRawValue() as Atendimento;
      this.entity.endereco.cep = response.cep;
      this.entity.endereco.rua = response.rua;
      this.entity.endereco.bairro = response.bairro;
      this.entity.endereco.numero = response.numero;
      this.entity.endereco.complemento = response.complemento;
      this.entity.endereco.cidade = { codigo: response.cidade.codigo, nome: response.cidade.nome, uf: response.cidade.uf };
      this.setForm(this.entity);

   }

   atualizarTelefone(response: Telefone[]) {
      this.entity = this.form.getRawValue() as Atendimento;
      this.entity.telefones = '';
      response.forEach(tel => {
         this.entity.telefones = this.entity.telefones + tel.numero + " ";
      });

      this.setForm(this.entity);
   }

   public cadatsrarCliente(): void {
      this.overlayRef = this.overlayService.open({
        templateRef: this.clienteFormOverlay,
        title: 'Cadastro de Cliente'
      });
    }
}
