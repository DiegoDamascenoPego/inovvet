import { SubgrupoService } from './../../../../service/cadastro/subgrupo.service';
import { Component, OnInit, Injector } from '@angular/core';
import { EntityBase } from 'app/model/entity-base';
import { PesquisaBase } from 'app/view/compartilhado/base/pesquisa-base';
import { Column, TypeField, TypeStyle, Columns, ColumnAction } from 'app/components/shared/table/table/table.component';

@Component({
  selector: 'app-pesquisa-subgrupo-form',
  templateUrl: './pesquisa-subgrupo-form.component.html',
})
export class PesquisaSubgrupoFormComponent extends PesquisaBase<EntityBase> implements OnInit {

 
   displayedColumns: Column[] = [
      new Columns("id", "Código", [TypeStyle.W100]),
      new Columns("nome", "Nome"),
      new ColumnAction("action")];
      
   tituloPagina = 'Gerenciador de Subgrupo';


   constructor(
      protected service: SubgrupoService, 
      protected injector: Injector) {
      super(service, injector);
   }

   ngOnInit() {

   }

   createFiltro() {
      this.filtro.obj = new EntityBase()
      this.filtro.obj = this.form.value;
   }

   create(): EntityBase {
      return new EntityBase();
   }

   setForm() {
      this.form = this.fb.group({
         nome: [''],
         ativo: [true]
      });
   }


}
