import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { CurrencyMaskModule, CurrencyMaskConfig, CURRENCY_MASK_CONFIG } from 'ng2-currency-mask';
import { NgxMaskModule, IConfig } from 'ngx-mask';


const maskConfig: Partial<IConfig> = {
  validation: false,
};

export const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
  align: 'right',
  allowNegative: true,
  decimal: '.',
  precision: 2,
  prefix: '',
  suffix: '',
  thousands: ' '
};


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CurrencyMaskModule,
    NgxMaskModule.forRoot(maskConfig),
  ],
  exports: [
    CurrencyMaskModule,
    NgxMaskModule
  ],
  providers: [
    { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig },
    DatePipe
  ],
})
export class PipeModule { }
