import { CategoriaService } from './../../../../service/cadastro/categoria.service';
import { Component, OnInit, Injector } from '@angular/core';
import { PesquisaBase } from 'app/view/compartilhado/base/pesquisa-base';
import { EntityBase } from 'app/model/entity-base';
import { TypeStyle, TypeField, Column, ColumnAction, Columns } from 'app/components/shared/table/table/table.component';

@Component({
  selector: 'app-pesquisa-categoria-form',
  templateUrl: './pesquisa-categoria-form.component.html',
})
export class PesquisaCategoriaFormComponent extends PesquisaBase<EntityBase> implements OnInit {

   displayedColumns: Column[] = [
      new Columns("id", "Código", [TypeStyle.W100]),
      new Columns("nome", "Nome"),
      new ColumnAction("action")];

   tituloPagina = 'Gerenciador de Categoria';


   constructor(
      protected service: CategoriaService,
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
