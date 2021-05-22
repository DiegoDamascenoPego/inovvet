import { Routes } from '@angular/router';
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
import { CadastroProdutoFormComponent } from './produto/cadastro-produto-form/cadastro-produto-form.component';
import { PesquisaProdutoFormComponent } from './produto/pesquisa-produto-form/pesquisa-produto-form.component';
import { AuthGuard } from 'app/_helpers/auth/auth.guard';
import { ProdutoResolver } from 'app/service/cadastro/produto/produto.resolver';
import { UnidadeService } from 'app/service/cadastro/produto/unidade.service';
import { UnidadeResolver } from 'app/service/cadastro/produto/unidade.resolver';
import { TipoProdutoResolver } from 'app/service/cadastro/produto/tipo-produto.resolver';

export const ProdutoRoutes: Routes = [
   {
      path: 'cadastro/categoria',
      children: [
         {
            path: '',
            component: CadastroCategoriaFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'CATEGORIA_CADASTRAR',
               title: 'Cadastro de Categoria'
            }
         },
         {
            path: 'gerenciador',
            component: PesquisaCategoriaFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'CATEGORIA_CONSULTAR',
               title: 'Gerenciador de Categoria'
            }
         },
         {
            path: ':id',
            component: CadastroCategoriaFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'CATEGORIA_EDITAR',
               title: 'Cadastro de Categoria'
            }
         }
      ]
   },
   {
      path: 'cadastro/classificacao',
      children: [
         {
            path: '',
            component: CadastroClassificacaoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'CLASSIFICACAO_CADASTRAR',
               title: 'Cadastro de Classificação'
            }
         },
         {
            path: 'gerenciador', component: PesquisaClassificacaoFormComponent,
            canActivate: [AuthGuard],
            data: {
               role: 'CLASSIFICACAO_CONSULTAR',
               title: 'Gerenciador de Classificação'
            }
         },
         {
            path: ':id', component: CadastroClassificacaoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'CLASSIFICACAO_EDITAR',
               title: 'Cadastro de Classificação'
            }
         },
      ]
   },
   {
      path: 'cadastro/departamento',
      children: [
         {
            path: '',
            component: CadastroDepartamentoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'DEPARTAMENTO_CADASTRAR',
               title: 'Cadastro de Departamento'
            }
         },
         {
            path: 'gerenciador', component: PesquisaDepartamentoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'DEPARTAMENTO_CONSULTAR',
               title: 'Gerenciador de Departamento'
            }
         },
         {
            path: ':id', component: CadastroDepartamentoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'DEPARTAMENTO_EDITAR',
               title: 'Cadastro de Departamento'
            }
         },
      ]
   },
   {
      path: 'cadastro/produto',
      children: [
         {
            path: '',
            component: CadastroProdutoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'PRODUTO_CADASTRAR',
               title: 'Cadastro de Produto'
            },
            resolve: {
               unidades: UnidadeResolver,
               tipos: TipoProdutoResolver
            }
         },
         {
            path: 'gerenciador', 
            component: PesquisaProdutoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'PRODUTO_CONSULTAR',
               title: 'Gerenciador de Produto'
            }
         },
         {
            path: ':id', 
            component: CadastroProdutoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'PRODUTO_EDITAR',
               title: 'Cadastro de Produto'
            }, 
            resolve: {
               entity: ProdutoResolver, 
               unidades: UnidadeResolver,
               tipos: TipoProdutoResolver
            }
         },
      ]
   },
   {
      path: 'cadastro/grupo',
      children: [
         {
            path: '',
            component: CadastroGrupoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'GRUPO_CADASTRAR',
               title: 'Cadastro de Grupo'
            }
         },
         {
            path: 'gerenciador',
            component: PesquisaGrupoFormComponent,
            canActivate: [AuthGuard],
            data: {
               role: 'GRUPO_CONSULTAR',
               title: 'Gerenciador de Grupo'
            }
         },
         {
            path: ':id',
            component: CadastroGrupoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'GRUPO_EDITAR',
               title: 'Cadastro de Grupo'
            }
         }
      ]
   },
   {
      path: 'cadastro/fabricante',
      children: [
         {
            path: '',
            component: CadastroFabricanteFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'FABRICANTE_CADASTRAR',
               title: 'Cadastro de Fabricante'
            }
         },
         {
            path: 'gerenciador',
            component: PesquisaFabricanteFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'FABRICANTE_CONSULTAR',
               title: 'Gerenciador de Fabricante'
            }
         },
         {
            path: ':id',
            component: CadastroFabricanteFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'FABRICANTE_EDITAR',
               title: 'Cadastro de Fabricante'
            }
         },
      ]
   },
   {
      path: 'cadastro/subgrupo',
      children: [
         {
            path: '', component:
               CadastroSubgrupoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'SUBGRUPO_CADASTRAR',
               title: 'Cadastro de Subgrupo'
            }
         },
         {
            path: 'gerenciador',
            component: PesquisaSubgrupoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'SUBGRUPO_CONSULTAR',
               title: 'Gerenciador de Subgrupo'
            }
         },
         {
            path: ':id',
            component: CadastroSubgrupoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'SUBGRUPO_EDITAR',
               title: 'Cadastro de Subgrupo'
            }
         },
      ]
   },



   {
      path: '**',
      redirectTo: 'pagina-nao-encontrada',
      pathMatch: 'full',
   },
];
