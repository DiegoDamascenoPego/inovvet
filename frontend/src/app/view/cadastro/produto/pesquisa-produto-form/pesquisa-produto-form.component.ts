
import { Component, OnInit, Injector } from '@angular/core';
import { Produto, ProdutoFiltro } from 'app/model/produto/produto';
import { PesquisaBase } from 'app/view/compartilhado/base/pesquisa-base';
import { ProdutoService } from '../../../../service/cadastro/produto/produto.service';
import { SimpleEntity } from 'app/model/entity-base';
import { TipoProdService } from 'app/service/cadastro/produto/tipo-prod.service';
import { TypeField, TypeStyle, Column, ColumnAction } from 'app/components/shared/table/table/table.component';

@Component({
   selector: 'app-pesquisa-produto-form',
   templateUrl: './pesquisa-produto-form.component.html',
})
export class PesquisaProdutoFormComponent extends PesquisaBase<Produto> implements OnInit {

   tituloPagina = 'Gerenciador de Produtos';


   displayedColumns: Column[] = [
      { definition: "id", type: TypeField.TEXT, header: "Código", style: [TypeStyle.W100] },
      { definition: "nome", type: TypeField.TEXT, header: "Nome", style: [TypeStyle.MW250] },
      { definition: "classificacao", type: TypeField.TEXT, header: "Classificação", style: [TypeStyle.W400],
      get(row: any, col: Column): string { return row[col.definition].descricaoCompleta }, },
      { definition: "marca", type: TypeField.TEXT, header: "Marca", style: [TypeStyle.W150],
      get(row: any, col: Column): string { return row[col.definition].nome }, },
      { definition: "precos", type: TypeField.CURRENCY, header: "Preço", style: [TypeStyle.W100],
      get(row: any, col: Column): string { return row[col.definition][0].preco }, },
      new ColumnAction("action")];


   tipos: SimpleEntity[] = [];


   constructor(
      protected service: ProdutoService,
      private tipoProdService: TipoProdService,
      protected injector: Injector) {
      super(service, injector);
   }

   ngOnInit() {      
      this.tipoProdService.listarTodos().then((response) => {
         this.tipos = response;
      });
   }

   createFiltro() {
      this.filtro.obj = new ProdutoFiltro();
      this.filtro.obj = this.form.getRawValue();
   }

   create(): Produto {
      return new Produto();
   }

   setForm() {
      this.form = this.fb.group({
         descricao: [''],
         tipo: [new SimpleEntity(1)],
         ativo: [true]
      });
   }

}
