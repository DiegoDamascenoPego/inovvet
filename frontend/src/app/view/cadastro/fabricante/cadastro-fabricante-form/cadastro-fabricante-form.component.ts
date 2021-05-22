import { FabricanteService } from './../../../../service/cadastro/fabricante.service';
import { Fabricante } from './../../../../model/fabricante/fabricante';
import { Component, OnInit, Injector } from '@angular/core';
import { FormBase } from 'app/view/compartilhado/base/form-base';

@Component({
   selector: 'app-cadastro-fabricante-form',
   templateUrl: './cadastro-fabricante-form.component.html',
})
export class CadastroFabricanteFormComponent extends FormBase<Fabricante> implements OnInit {
   
   constructor(
      protected service: FabricanteService,
      protected injector: Injector
   ) {
      super(service, injector);
   }
   ngOnInit(): void {
   }

   setForm(entity: Fabricante) {
      return new Fabricante();
   }
   newEntity(): Fabricante {
      throw new Error("Method not implemented.");
   }


   setFormToEntity() {

   }
   setEntity(response: any) {

   }

}
