import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MAT_MOMENT_DATE_ADAPTER_OPTIONS, MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { RouterModule } from '@angular/router';
import { MaterialModule } from 'app/components/shared/material/material.module';
import { DATE_FORMATS } from 'app/util/format-utils';
import { SharedModule } from './../compartilhado/shared.module';
import { AtendimentoFormComponent } from './atendimento/atendimento-form/atendimento-form.component';
import { PainelAtendimentoFormComponent } from './atendimento/painel-atendimento-form/painel-atendimento-form.component';
import { GerenciadorRoutes } from './gerenciador.routing';
import { ProdutoModule } from '../cadastro/produto.module';
import { PipeModule } from 'app/components/shared/pipe.module';
import { CamposModule } from 'app/components/shared/campos/campos.module';
import { BotaoModule } from 'app/components/shared/botoes/botao.module';
import { TabelaModule } from 'app/components/shared/table/tabela.module';
import { ClienteModule } from '../cadastro/cliente.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OverlayModule } from 'app/components/overlay/overlay.module';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    RouterModule.forChild(GerenciadorRoutes),
    MaterialModule,
    SharedModule,
    CamposModule,
    BotaoModule,
    TabelaModule,
    ProdutoModule,
    PipeModule, 
    ClienteModule,
    OverlayModule
  ],
  declarations: [
    PainelAtendimentoFormComponent,
    AtendimentoFormComponent
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' },
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: { useUtc: true } },
    { provide: MAT_DATE_FORMATS, useValue: DATE_FORMATS }
  ],
})
export class GerenciadorModule { }
