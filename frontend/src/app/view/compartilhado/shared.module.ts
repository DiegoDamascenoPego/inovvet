import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MaterialModule } from 'app/components/shared/material/material.module';
import { ArquivoComponent } from './arquivo/arquivo.component';
import { ClassificacaoFiltroComponent } from './classificacao-filtro/classificacao-filtro.component';
import { ListaClassificacaoComponent } from './classificacao/lista-classificacao/lista-classificacao.component';
import { EnderecoComponent } from './endereco/endereco.component';
import { HeaderCadastroFormComponent } from './header-cadastro-form/header-cadastro-form.component';
import { HeaderPesquisaFormComponent } from './header-pesquisa-form/header-pesquisa-form.component';
import { PesquisaMarcaComponent } from './marca/pesquisa-marca/pesquisa-marca.component';
import { MasterFormComponent } from './master-form/master-form.component';
import { DialogComponent } from './modal/dialog/dialog.component';
import { OverlayComponent } from './overlay/overlay.component';
import { CadastroPrecoFormComponent } from './preco/cadastro-preco-form/cadastro-preco-form.component';
import { RacaComponent } from './raca/raca.component';
import { SeguimentoComponent } from './seguimento/seguimento.component';
import { SetorComponent } from './setor/setor.component';
import { TelefoneComponent } from './telefone/telefone.component';
import { PipeModule } from 'app/components/shared/pipe.module';
import { FiltroProdutoComponent } from './produto/filtro-produto/filtro-produto.component';
import { CamposModule } from 'app/components/shared/campos/campos.module';
import { ExtrasModule } from 'app/components/shared/extras/extras.module';
import { BotaoModule } from 'app/components/shared/botoes/botao.module';
import { TabelaModule } from 'app/components/shared/table/tabela.module';

@NgModule({
   imports: [
      CommonModule,
      RouterModule,
      FormsModule,
      ReactiveFormsModule,
      MaterialModule,
      CamposModule,
      ExtrasModule,
      PipeModule,
      BotaoModule,
      TabelaModule,
   ],
   declarations: [
      OverlayComponent,
      EnderecoComponent,
      TelefoneComponent,
      RacaComponent,
      HeaderCadastroFormComponent,
      HeaderPesquisaFormComponent,
      SetorComponent,
      DialogComponent,
      MasterFormComponent,
      SeguimentoComponent,
      ClassificacaoFiltroComponent,
      ListaClassificacaoComponent,
      ArquivoComponent,
      PesquisaMarcaComponent,
      CadastroPrecoFormComponent,
      FiltroProdutoComponent,
      ],
   exports: [
      EnderecoComponent,
      TelefoneComponent,
      RacaComponent,
      OverlayComponent,
      ArquivoComponent,
      HeaderCadastroFormComponent,
      HeaderPesquisaFormComponent,
      SetorComponent,
      DialogComponent,
      MasterFormComponent,
      SeguimentoComponent,
      ClassificacaoFiltroComponent,
      ListaClassificacaoComponent,
      PesquisaMarcaComponent,
      CadastroPrecoFormComponent,
      FiltroProdutoComponent
   ],
   entryComponents: [
      DialogComponent
   ],
   providers: [
    
   ]
})
export class SharedModule { }
