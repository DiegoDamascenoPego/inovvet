import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BotaoModule } from 'app/components/shared/botoes/botao.module';
import { CamposModule } from 'app/components/shared/campos/campos.module';
import { TabelaModule } from 'app/components/shared/table/tabela.module';
import { ExtrasModule } from 'app/components/shared/extras/extras.module';
import { MaterialModule } from 'app/components/shared/material/material.module';
import { PipeModule } from 'app/components/shared/pipe.module';
import { SharedModule } from '../compartilhado/shared.module';
import { SegurancaModule } from '../seguranca/seguranca/seguranca.module';
import { CadastroTipoAtendimentoFormComponent } from './atendimento/cadastro-tipo-atendimento-form/cadastro-tipo-atendimento-form.component';
import { PesquisaTipoAtendimentoComponent } from './atendimento/pesquisa-tipo-atendimento/pesquisa-tipo-atendimento.component';
import { ClienteRoutes } from './cliente.routing';
import { CadastroClienteFormComponent } from './cliente/cadastro-cliente-form/cadastro-cliente-form.component';
import { CadastroPetFormComponent } from './cliente/cadastro-pet-form/cadastro-pet-form.component';
import { GridPetFormComponent } from './cliente/grid-pet-form/grid-pet-form.component';
import { PesquisaClienteFormComponent } from './cliente/pesquisa-cliente-form/pesquisa-cliente-form.component';
import { OverlayModule } from 'app/components/overlay/overlay.module';




@NgModule({
   imports: [
      CommonModule,
      MaterialModule,
      FormsModule,
      ReactiveFormsModule,
      RouterModule.forChild(ClienteRoutes),
      SharedModule,
      SegurancaModule,
      ExtrasModule,
      PipeModule,
      CamposModule,
      BotaoModule,
      TabelaModule, 
      OverlayModule
   ],
   declarations: [
      CadastroClienteFormComponent,
      CadastroPetFormComponent,
      GridPetFormComponent,
      PesquisaClienteFormComponent,
      PesquisaTipoAtendimentoComponent,
      CadastroTipoAtendimentoFormComponent
   ],
   providers: [],
   exports: [
      CadastroClienteFormComponent
   ]
})
export class ClienteModule { }