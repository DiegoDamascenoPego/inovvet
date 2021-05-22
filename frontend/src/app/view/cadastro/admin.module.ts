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
import { AdminRoutes } from './admin-routing';
import { CadastroLojaFormComponent } from './administrativo/cadastro-loja-form/cadastro-loja-form.component';
import { PesquisaLojaFormComponent } from './administrativo/pesquisa-loja-form/pesquisa-loja-form.component';



@NgModule({
   declarations: [
      CadastroLojaFormComponent,
      PesquisaLojaFormComponent],
   imports: [
      CommonModule,
      MaterialModule,
      FormsModule,
      ReactiveFormsModule,
      RouterModule.forChild(AdminRoutes),
      SharedModule,
      SegurancaModule,
      ExtrasModule,
      PipeModule,
      CamposModule,
      BotaoModule,
      TabelaModule
   ]
})
export class AdminModule { }
