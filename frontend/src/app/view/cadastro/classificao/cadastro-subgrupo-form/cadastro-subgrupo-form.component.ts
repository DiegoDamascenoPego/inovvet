import { SubgrupoService } from 'app/service/cadastro/subgrupo.service';
import { Component, OnInit, Injector } from '@angular/core';
import { FormBase } from 'app/view/compartilhado/base/form-base';
import { EntityBase } from 'app/model/entity-base';
import { Validators } from '@angular/forms';

@Component({
   selector: 'app-cadastro-subgrupo-form',
   templateUrl: './cadastro-subgrupo-form.component.html',
})
export class CadastroSubgrupoFormComponent extends FormBase<EntityBase> {

   constructor(
      protected service: SubgrupoService,
      protected injector: Injector,
   ) {
      super(service, injector);
   }

   newEntity(): EntityBase {
      return new EntityBase();
   }

   setForm(entity: EntityBase) {
      this.form = this.fb.group({
         id: [{ value: entity.id, disabled: true }],
         nome: [entity.nome, [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
         ativo: [entity.ativo, [Validators.required]],
      });
   }

   setFormToEntity() {
      this.entity = this.form.getRawValue() as EntityBase;
   }
   setEntity(response: any) {
      this.entity = response;
      this.setForm(this.entity);
   }

}
