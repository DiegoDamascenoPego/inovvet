import { Component, OnInit } from '@angular/core';

export interface AgendaDetalhe {

   fazenda: string;

   texto: String;
   lote: string

}

export interface Agenda {
   data: string;
   agendadetalhe: AgendaDetalhe[];
}

@Component({
   selector: 'app-agenda',
   templateUrl: './agenda.component.html',
})
export class AgendaComponent implements OnInit {

   planner: Agenda[] = [

      {
         data: 'Segunda feira - 18-01-2021', agendadetalhe: [
            { fazenda: 'VSM I - Cascalheira', texto: 'Repasse Touro', lote: '2 LT2 Parida 06/09 a 28/09' },
         ]
      },
      {
         data: 'Sexta feira - 22-01-2021', agendadetalhe: [
            { fazenda: 'VSM I - Cascalheira', texto: 'Diagnostico', lote: '10 LT Novilhas inseminada' },
         ]
      },

      {
         data: 'Terça feira - 26-01-2021', agendadetalhe: [
            { fazenda: 'VSM I - Cascalheira', texto: 'Vacina Brucelose', lote: '2 LT2 Parida 06/09 a 28/09' },
            { fazenda: 'VSM I - Cascalheira', texto: 'Vermifugar bezerros', lote: '2 LT2 Parida 06/09 a 28/09' },
         ]
      },
      {
         data: 'Terça feira - 28-01-2021', agendadetalhe: [
            { fazenda: 'VSM I - Cascalheira', texto: 'Identificar bezerros', lote: '4 LT04 Parida 02/12 a 30/12' },
            { fazenda: 'VSM I - Cascalheira', texto: 'Vermifugar bezerros', lote: '4 LT04 Parida 02/12 a 30/12' },
         ]
      },
   ];

   constructor() { }

   ngOnInit(): void {
   }

}
