import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ExtrasModule } from 'app/components/shared/extras/extras.module';
import { MaterialModule } from 'app/components/shared/material/material.module';
import { ErrorDetailComponent } from './error-detail/error-detail.component';
import { InputActiveComponent } from './input-active/input-active.component';
import { InputDateComponent } from './input-date/input-date.component';
import { InputMaskComponent } from './input-mask/input-mask.component';
import { InputNumberPercentComponent } from './input-number-percent/input-number-percent.component';
import { InputSelectEntityComponent } from './input-select-entity/input-select-entity.component';
import { InputSelectComponent } from './input-select/input-select.component';
import { InputTextComponent } from './input-text/input-text.component';
import { InputSelectEntityBaseComponent } from './input-select-entity-base/input-select-entity-base.component';
import { InputCurrencyComponent } from './input-currency/input-currency.component';
import { InputSelectLojaComponent } from './input-select-loja/input-select-loja.component';
import { ColumnComponent } from './column/column.component';
import { PipeModule } from 'app/components/shared/pipe.module';
import { InputAutoCompleteComponent } from './input-auto-complete/input-auto-complete.component';
import { InputSelectVagaComponent } from './input-select-vaga/input-select-vaga.component';
import { InputNumberComponent } from './input-number/input-number.component';
import { InputDateRangeComponent } from './input-date-range/input-date-range.component';
import { InputTelefoneComponent } from './input-telefone/input-telefone.component';
import { InputPasswordComponent } from './input-password/input-password.component';
import { ErrorDetailFormComponent } from './error-detail-form/error-detail-form.component';
import { InputTextExComponent } from './input-text-ex/input-text-ex.component';



@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    ExtrasModule,
    PipeModule,
  ],
  declarations: [
    InputTextComponent,
    InputNumberPercentComponent,
    InputActiveComponent,
    ErrorDetailComponent,
    InputSelectComponent,
    InputSelectEntityComponent,
    InputDateComponent,
    InputMaskComponent,
    InputSelectEntityComponent,
    InputSelectEntityBaseComponent,
    InputCurrencyComponent,
    InputSelectLojaComponent,
    ColumnComponent,
    InputAutoCompleteComponent,
    InputSelectVagaComponent,
    InputNumberComponent,
    InputDateRangeComponent,
    InputTelefoneComponent,
    InputPasswordComponent,
    ErrorDetailFormComponent,
    InputTextExComponent
  ],
  exports: [
    InputTextComponent,
    InputNumberPercentComponent,
    InputActiveComponent,
    InputSelectComponent,
    InputSelectEntityComponent,
    InputDateComponent,
    InputMaskComponent,
    InputSelectEntityBaseComponent,
    InputCurrencyComponent,
    InputSelectLojaComponent,
    ColumnComponent,
    InputAutoCompleteComponent,
    InputSelectVagaComponent,
    InputNumberComponent,
    InputDateRangeComponent,
    InputTelefoneComponent,
    InputPasswordComponent, 
    ErrorDetailFormComponent
  ]
})
export class CamposModule { }
