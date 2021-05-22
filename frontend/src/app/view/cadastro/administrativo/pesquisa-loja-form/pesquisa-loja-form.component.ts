import { Component, Injector, OnInit } from '@angular/core';
import { LojaCadastro, LojaFiltro } from 'app/model/Sistema/loja';
import { LojaService } from 'app/service/cadastro/loja.service';
import { Column, ColumnAction, Columns, TypeStyle } from 'app/components/shared/table/table/table.component';
import { PesquisaBase } from 'app/view/compartilhado/base/pesquisa-base';

@Component({
   selector: 'app-pesquisa-loja-form',
   templateUrl: './pesquisa-loja-form.component.html',
})
export class PesquisaLojaFormComponent extends PesquisaBase<LojaCadastro> implements OnInit {

   displayedColumns: Column[] = [
      new Columns("id", "Código", [TypeStyle.W100]),
      new Columns("nomeFantasia", "Nome"),
      new Columns("documento", "CNPJ/CPF"),
      new ColumnAction("action")];

   ngOnInit(): void {
   }

   constructor(
      protected service: LojaService,
      protected injector: Injector) {
      super(service, injector);
   }
   createFiltro() {
      this.filtro.obj = new LojaFiltro()
      this.filtro.obj = this.form.value;
   }
   create(): LojaCadastro {
     return new LojaCadastro();
   }
   setForm() {
      this.form = this.fb.group({
         nomeFantasia: [''],
         ativo: [true]
      });
   }


}
