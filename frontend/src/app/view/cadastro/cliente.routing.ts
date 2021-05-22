import { Routes } from '@angular/router';
import { PesquisaTipoAtendimentoComponent } from './atendimento/pesquisa-tipo-atendimento/pesquisa-tipo-atendimento.component';
import { CadastroClienteFormComponent } from './cliente/cadastro-cliente-form/cadastro-cliente-form.component';
import { CadastroPetFormComponent } from './cliente/cadastro-pet-form/cadastro-pet-form.component';
import { PesquisaClienteFormComponent } from './cliente/pesquisa-cliente-form/pesquisa-cliente-form.component';
import { CadastroTipoAtendimentoFormComponent } from './atendimento/cadastro-tipo-atendimento-form/cadastro-tipo-atendimento-form.component';
import { AuthGuard } from 'app/_helpers/auth/auth.guard';
import { ClienteResolver } from 'app/service/cadastro/cliente/cliente.resolver';


export const ClienteRoutes: Routes = [
   {
      path: 'cadastro/atendimento',
      children: [
         {
            path: '',
            component: CadastroTipoAtendimentoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'TIPOATENDIMENTO_CADASTRAR',
               title: 'Cadastro de Tipo Atendimento'
            }
         },
         {
            path: 'gerenciador',
            component: PesquisaTipoAtendimentoComponent, canActivate: [AuthGuard],
            data: {
               role: 'TIPOATENDIMENTO_CONSULTAR',
               title: 'Gerenciador de Tipo Atendimento'
            }
         },
         {
            path: ':id',
            component: CadastroTipoAtendimentoFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'TIPOATENDIMENTO_EDITAR',
               title: 'Cadastro de Tipo Atendimento'
            }
         },
      ]
   },
   {
      path: 'cadastro/cliente',
      children: [
         {
            path: '',
            component: CadastroClienteFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'CLIENTE_CADASTRAR',
               title: 'Cadastro de Cliente'
            },
         },
         {
            path: 'gerenciador',
            component: PesquisaClienteFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'CLIENTE_CONSULTAR',
               title: 'Gerenciador de Cliente'
            }
         },
         {
            path: ':id',
            component: CadastroClienteFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'CLIENTE_EDITAR',
               title: 'Cadastro de Cliente'
            },
            resolve:{
               entity: ClienteResolver
            }
         },
         {
            path: ':id/pets',
            component: CadastroPetFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'CLIENTE_CADASTRAR',
               title: 'Cadastro de Animais'
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
