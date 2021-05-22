import { Component, Injector } from '@angular/core';
import { Validators } from '@angular/forms';
import { Departamento } from 'app/model/classificao/departamento';
import { Seguimento } from 'app/model/seguimento/seguimento';
import { DepartamentoService } from 'app/service/cadastro/departamento.service';
import { FormBase } from 'app/view/compartilhado/base/form-base';

@Component({
   selector: 'app-cadastro-departamento-form',
   templateUrl: './cadastro-departamento-form.component.html',
})
export class CadastroDepartamentoFormComponent extends FormBase<Departamento> {

   seguimento: Seguimento = new Seguimento();

   constructor(
      protected service: DepartamentoService,
      protected injector: Injector,
   ) {
      super(service, injector);
   }

   setForm(entity: Departamento) {
      this.form = this.fb.group({
         id: [{ value: entity.id, disabled: true }],
         nome: [entity.nome, [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
         seguimento:[entity.seguimento, [Validators.required]],
         ativo: [entity.ativo, [Validators.required]]
      });
   }
   newEntity(): Departamento {
      return new Departamento();
   }

   setFormToEntity() {
      this.entity = this.form.getRawValue() as Departamento;
   }
   setEntity(response: any) {
      this.entity = response;
      this.seguimento = this.entity.seguimento;

      if (!this.seguimento) {
         this.seguimento = new Seguimento();
      }

      this.setForm(this.entity);
   }

   changeSeguimento(event) {
      this.seguimento.id = event;
   }

}
