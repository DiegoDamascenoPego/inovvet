import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BotaoModule } from 'app/components/shared/botoes/botao.module';
import { CamposModule } from 'app/components/shared/campos/campos.module';
import { TabelaModule } from 'app/components/shared/table/tabela.module';
import { ExtrasModule } from 'app/components/shared/extras/extras.module';
import { MaterialModule } from 'app/components/shared/material/material.module';
import { SharedModule } from '../compartilhado/shared.module';
import { SegurancaModule } from '../seguranca/seguranca/seguranca.module';
import { CadastroCategoriaFormComponent } from './classificao/cadastro-categoria-form/cadastro-categoria-form.component';
import { CadastroClassificacaoFormComponent } from './classificao/cadastro-classificacao-form/cadastro-classificacao-form.component';
import { CadastroDepartamentoFormComponent } from './classificao/cadastro-departamento-form/cadastro-departamento-form.component';
import { CadastroGrupoFormComponent } from './classificao/cadastro-grupo-form/cadastro-grupo-form.component';
import { CadastroSubgrupoFormComponent } from './classificao/cadastro-subgrupo-form/cadastro-subgrupo-form.component';
import { PesquisaCategoriaFormComponent } from './classificao/pesquisa-categoria-form/pesquisa-categoria-form.component';
import { PesquisaClassificacaoFormComponent } from './classificao/pesquisa-classificacao-form/pesquisa-classificacao-form.component';
import { PesquisaDepartamentoFormComponent } from './classificao/pesquisa-departamento-form/pesquisa-departamento-form.component';
import { PesquisaGrupoFormComponent } from './classificao/pesquisa-grupo-form/pesquisa-grupo-form.component';
import { PesquisaSubgrupoFormComponent } from './classificao/pesquisa-subgrupo-form/pesquisa-subgrupo-form.component';
import { CadastroFabricanteFormComponent } from './fabricante/cadastro-fabricante-form/cadastro-fabricante-form.component';
import { PesquisaFabricanteFormComponent } from './fabricante/pesquisa-fabricante-form/pesquisa-fabricante-form.component';
import { ProdutoRoutes } from './produto.routing';
import { CadastroProdutoFormComponent } from './produto/cadastro-produto-form/cadastro-produto-form.component';
import { PesquisaProdutoFormComponent } from './produto/pesquisa-produto-form/pesquisa-produto-form.component';
import { PipeModule } from 'app/components/shared/pipe.module';



@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(ProdutoRoutes),
    SharedModule,
    SegurancaModule,
    ExtrasModule,
    PipeModule,
    CamposModule,
    BotaoModule,
    TabelaModule,
  ],
  declarations: [
    PesquisaProdutoFormComponent,
    CadastroProdutoFormComponent,
    CadastroClassificacaoFormComponent,
    PesquisaClassificacaoFormComponent,
    CadastroDepartamentoFormComponent,
    CadastroCategoriaFormComponent,
    CadastroGrupoFormComponent,
    CadastroSubgrupoFormComponent,
    PesquisaSubgrupoFormComponent,
    PesquisaGrupoFormComponent,
    PesquisaCategoriaFormComponent,
    PesquisaDepartamentoFormComponent,
    CadastroFabricanteFormComponent,
    PesquisaFabricanteFormComponent,
  ],
})
export class ProdutoModule { }
