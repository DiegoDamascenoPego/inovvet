import { DepartamentoService } from './../../../../service/cadastro/departamento.service';
import { EntityBase } from './../../../../model/entity-base';
import { Component, OnInit, Injector } from '@angular/core';
import { PesquisaBase } from 'app/view/compartilhado/base/pesquisa-base';
import { TypeField, Column, TypeStyle, Columns, ColumnAction } from 'app/components/shared/table/table/table.component';

@Component({
  selector: 'app-pesquisa-departamento-form',
  templateUrl: './pesquisa-departamento-form.component.html',
})
export class PesquisaDepartamentoFormComponent  extends PesquisaBase<EntityBase> implements OnInit {

   displayedColumns: Column[] = [
      new Columns("id", "Código", [TypeStyle.W100]),
      new Columns("nome", "Nome"),
      new ColumnAction("action")];
      
   tituloPagina = 'Gerenciador de Departamento';


   constructor(
      protected service: DepartamentoService, 
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
