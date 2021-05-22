import { CommonModule, CurrencyPipe, DecimalPipe, PercentPipe } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { MaterialModule } from 'app/components/shared/material/material.module';
import { MaskPipe } from 'ngx-mask';
import { BotaoModule } from '../botoes/botao.module';
import { TableComponent } from './table/table.component';



@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    BotaoModule,
  ],

 declarations: [
   TableComponent
  ],
 exports: [
   TableComponent
  ],
 schemas: [
  CUSTOM_ELEMENTS_SCHEMA
],
providers:[DecimalPipe, CurrencyPipe, PercentPipe, MaskPipe]

})
export class TabelaModule { }
