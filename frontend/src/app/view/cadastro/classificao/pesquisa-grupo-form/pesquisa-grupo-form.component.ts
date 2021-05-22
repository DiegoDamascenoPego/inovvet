import { Component, Injector, OnInit } from '@angular/core';
import { EntityBase } from 'app/model/entity-base';
import { PesquisaBase } from 'app/view/compartilhado/base/pesquisa-base';
import { GrupoService } from './../../../../service/cadastro/grupo.service';
import { TypeField, Column, TypeStyle, ColumnAction, Columns } from 'app/components/shared/table/table/table.component';
import { type } from 'os';

@Component({
   selector: 'app-pesquisa-grupo-form',
   templateUrl: './pesquisa-grupo-form.component.html',
})
export class PesquisaGrupoFormComponent extends PesquisaBase<EntityBase> {

   displayedColumns: Column[] = [
      new Columns("id", "Código", [TypeStyle.W100]),
      new Columns("nome", "Nome"),
      new ColumnAction("action")];

   tituloPagina = 'Gerenciador de Grupo';


   constructor(
      protected service: GrupoService,
      protected injector: Injector) {
      super(service, injector);
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
