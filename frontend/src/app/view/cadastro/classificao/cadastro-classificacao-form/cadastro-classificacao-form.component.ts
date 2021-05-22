import { Departamento } from './../../../../model/classificao/departamento';
import { Component, Injector, OnInit } from '@angular/core';
import { Validators, FormControl } from '@angular/forms';
import { Classificao } from 'app/model/classificao/classificao';
import { FormBase } from 'app/view/compartilhado/base/form-base';
import { EntityBase } from 'app/model/entity-base';
import { ClassificaoService } from 'app/service/cadastro/classificao.service';
import { DepartamentoService } from 'app/service/cadastro/departamento.service';
import { CategoriaService } from 'app/service/cadastro/categoria.service';
import { GrupoService } from 'app/service/cadastro/grupo.service';
import { SubgrupoService } from 'app/service/cadastro/subgrupo.service';

@Component({
   selector: 'app-cadastro-classificacao-form',
   templateUrl: './cadastro-classificacao-form.component.html',
})
export class CadastroClassificacaoFormComponent extends FormBase<Classificao> implements OnInit {
   

   tituloPagina = 'Cadastro de classificação';

   departamentos: EntityBase[];
   categorias: EntityBase[];
   grupos: EntityBase[];
   subgrupos: EntityBase[];

   departamentoFB = new FormControl();

   constructor(
      protected service: ClassificaoService,
      protected injector: Injector,
      private departamentoService: DepartamentoService,
      private categoriaService: CategoriaService,
      private grupoService: GrupoService,
      private subgrupoService: SubgrupoService
   ) {
      super(service, injector);
   }

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
      super.ngOnInit();

   }

   newEntity(): Classificao {
      return new Classificao;
   }

   setForm(entity: Classificao) {
      this.form = this.fb.group({
         id: [{ value: entity.id, disabled: true }],
         departamento: [entity.departamento, Validators.required],
         categoria: [entity.categoria, Validators.required],
         grupo: [entity.grupo, Validators.required],
         subgrupo: [entity.subgrupo, Validators.required],
         ativo: [entity.ativo]
      });
   }

   setFormToEntity() {
      this.entity = this.form.getRawValue() as Classificao;
   }

   setEntity(response: any) {
      this.entity = response;
      this.setForm(this.entity);
   }

   compareObj(obj: any, entity: any) {
      return obj && entity ? obj.id === entity.id : obj === entity;
   }
}
