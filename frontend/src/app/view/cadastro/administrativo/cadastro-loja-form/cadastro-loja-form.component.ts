import { Component, Injector, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { Endereco } from 'app/model/endereco/endereco';
import { EnumTipoLoja } from 'app/model/loja/enum-tipo-loja.enum';
import { EnumTipoPessoa } from 'app/model/loja/enum-tipo-pessoa.enum';
import { Loja, LojaCadastro } from 'app/model/Sistema/loja';
import { CategoriaService } from 'app/service/cadastro/categoria.service';
import { LojaService } from 'app/service/cadastro/loja.service';
import { FormBase } from 'app/view/compartilhado/base/form-base';
import { EnumValues } from 'enum-values';

@Component({
   selector: 'app-cadastro-loja-form',
   templateUrl: './cadastro-loja-form.component.html',
})
export class CadastroLojaFormComponent extends FormBase<LojaCadastro> {

   opcoes = EnumValues.getNamesAndValues(EnumTipoPessoa);

   ISCNPJ = EnumTipoPessoa.CNPJ;

   tipoLoja = EnumValues.getNamesAndValues(EnumTipoLoja);

   constructor(
      protected service: LojaService,
      protected injector: Injector,
   ) {
      super(service, injector);
   }

   setForm(entity: LojaCadastro) {
      this.form = this.fb.group({
         id: [{ value: entity.id, disabled: true }],
         tipoDocumento: [entity.tipoDocumento, [Validators.required]],
         nomeFantasia: [entity.nomeFantasia, [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
         razaoSocial: [entity.razaoSocial, [Validators.required, Validators.minLength(2), Validators.maxLength(150)]],
         documento: [entity.documento, [Validators.required]],
         telefone: [entity.telefone, [Validators.required]],
         email: [entity.email, [Validators.email]],
         ie: [entity.ie, [Validators.required]],
         tipoLoja: [entity.tipoLoja, [Validators.required]],
         ativo: [entity.ativo, Validators.required],
         endereco: this.fb.group({
            id: [{ value: entity.endereco.id, disabled: true }],
            rua: [entity.endereco.rua, [Validators.required]],
            numero: [entity.endereco.numero, [Validators.required]],
            bairro: [entity.endereco.bairro, [Validators.required]],
            cep: [entity.endereco.cep, [Validators.required]],
            cidade: [entity.endereco.cidade, [Validators.required]],
            uf: [{ sigla: entity.endereco.cidade.uf }],
            complemento: [entity.endereco.complemento]
         }),

      });
   }

   newEntity(): LojaCadastro {
      return new LojaCadastro();
   }
   setFormToEntity() {
      this.entity = this.form.getRawValue() as LojaCadastro;
   }
   setEntity(response: any) {
      this.entity = response;
      this.setForm(this.entity);
   }

   atualizarEndereco(response: Endereco) {
      this.entity = this.form.getRawValue() as LojaCadastro;
      this.entity.endereco.cep = response.cep;
      this.entity.endereco.rua = response.rua;
      this.entity.endereco.bairro = response.bairro;
      this.entity.endereco.numero = response.numero;
      this.entity.endereco.complemento = response.complemento;
      this.entity.endereco.cidade = {
         codigo: response.cidade.codigo,
         nome: response.cidade.nome,
         uf: response.cidade.uf,
      };

      this.setForm(this.entity);
   }
}
