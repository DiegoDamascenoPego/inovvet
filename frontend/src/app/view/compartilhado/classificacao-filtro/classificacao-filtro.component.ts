import { Component, OnInit, Input } from '@angular/core';
import { DepartamentoService } from 'app/service/cadastro/departamento.service';
import { CategoriaService } from 'app/service/cadastro/categoria.service';
import { GrupoService } from 'app/service/cadastro/grupo.service';
import { SubgrupoService } from 'app/service/cadastro/subgrupo.service';
import { EntityBase } from 'app/model/entity-base';
import { FormControl } from '@angular/forms';

@Component({
   selector: 'app-classificacao-filtro',
   templateUrl: './classificacao-filtro.component.html',
})
export class ClassificacaoFiltroComponent implements OnInit {

   @Input() departamentoFB = new FormControl();
   @Input() categoriaFB = new FormControl();
   @Input() grupoFB = new FormControl();
   @Input() subgrupoFB = new FormControl();

   departamentos: EntityBase[];
   categorias: EntityBase[];
   grupos: EntityBase[];
   subgrupos: EntityBase[];

   constructor(
      private departamentoService: DepartamentoService,
      private categoriaService: CategoriaService,
      private grupoService: GrupoService,
      private subgrupoService: SubgrupoService
   ) { }

   ngOnInit(): void {

      this.departamentoService.listarTodos().then((response) => {
         this.departamentos = response;
      });

      this.categoriaService.listarTodos().then((response) => {
         this.categorias = response;
      });

      this.grupoService.listarTodos().then((response) => {
         this.grupos = response;
      });

      this.subgrupoService.listarTodos().then((response) => {
         this.subgrupos = response;
      });
   }

}
