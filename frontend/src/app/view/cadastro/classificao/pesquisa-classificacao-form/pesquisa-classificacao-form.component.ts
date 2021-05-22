import { Component, OnInit, Injector } from '@angular/core';
import { Classificao } from 'app/model/classificao/classificao';
import { EntityBase } from 'app/model/entity-base';
import { CategoriaService } from 'app/service/cadastro/categoria.service';
import { ClassificaoService } from 'app/service/cadastro/classificao.service';
import { DepartamentoService } from 'app/service/cadastro/departamento.service';
import { GrupoService } from 'app/service/cadastro/grupo.service';
import { SubgrupoService } from 'app/service/cadastro/subgrupo.service';
import { PesquisaBase } from 'app/view/compartilhado/base/pesquisa-base';
import { FormControl } from '@angular/forms';
import { TypeStyle, TypeField, Column, Columns, ColumnAction, ColumnEntity } from 'app/components/shared/table/table/table.component';

@Component({
   selector: 'app-pesquisa-classificacao-form',
   templateUrl: './pesquisa-classificacao-form.component.html',
})
export class PesquisaClassificacaoFormComponent extends PesquisaBase<Classificao> {

   displayedColumns: Column[] = [
      new Columns("id", "Código", [TypeStyle.W100]),
      new ColumnEntity("departamento", "Departamento"),
      new ColumnEntity("categoria", "Categoria"),
      new ColumnEntity("grupo", "Grupo"),
      new ColumnEntity("subgrupo", "SubGrupo"),
      new ColumnAction("action")];  


   tituloPagina = 'Gerenciador de Classificação';

   departamentoFB = new FormControl();
   categoriaFB = new FormControl();
   grupoFB = new FormControl();
   subgrupoFB = new FormControl();

   constructor(
      protected service: ClassificaoService,
      protected injector: Injector,
      private departamentoService: DepartamentoService,
      private categoriaService: CategoriaService,
      private grupoService: GrupoService,
      private subgrupoService: SubgrupoService) {
      super(service, injector);
   }

   createFiltro() {
      this.filtro.obj = new Classificao();
      this.filtro.obj = this.form.value;
      this.filtro.obj.departamentos = this.departamentoFB.value;
      this.filtro.obj.categorias = this.categoriaFB.value;
      this.filtro.obj.grupos = this.grupoFB.value;
      this.filtro.obj.subgrupos = this.subgrupoFB.value;
   }
   create(): Classificao {
      return new Classificao();
   }
   setForm() {

      this.form = this.fb.group({
         ativo: [true]
      });

   }

   onDescricao(event): string {
      return event.departamento.nome + ' / ' + event.categoria.nome + ' / ' + event.grupo.nome + ' / ' + event.subgrupo.nome;
   }


}
