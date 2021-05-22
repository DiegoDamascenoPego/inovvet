import { Component, Injector } from '@angular/core';
import { TipoAtendimento } from 'app/model/atendimento/tipo-atendimento';
import { PesquisaBase } from 'app/view/compartilhado/base/pesquisa-base';
import { TipoAtendimentoFiltro } from './../../../../model/atendimento/tipo-atendimento';
import { TipoAtendimentoService } from './../../../../service/cadastro/tipo-atendimento.service';
import { Column, TypeField, TypeStyle, ColumnAction } from 'app/components/shared/table/table/table.component';

@Component({
   selector: 'app-pesquisa-tipo-atendimento',
   templateUrl: './pesquisa-tipo-atendimento.component.html',
   styleUrls: ['./pesquisa-tipo-atendimento.component.scss']
})
export class PesquisaTipoAtendimentoComponent extends PesquisaBase<TipoAtendimento>  {

   displayedColumns: Column[] = [
      { definition: "id", type: TypeField.TEXT, header: "Código", style: [TypeStyle.W100] },
      { definition: "nome", type: TypeField.TEXT, header: "Nome", style: [TypeStyle.WA400] },
      new ColumnAction("action")];

   tituloPagina = 'Gerenciador de tipo atendimento';

   constructor(
      protected service: TipoAtendimentoService,
      protected injector: Injector) {
      super(service, injector);
   }

   setForm() {
      this.form = this.fb.group({
         nome: [''],
         ativo: [true]
      });
   }

   create(): TipoAtendimento {
      return new TipoAtendimento();
   }

   createFiltro() {
      this.filtro.obj = new TipoAtendimentoFiltro();
      this.filtro.obj = this.form.value;
   }
}
