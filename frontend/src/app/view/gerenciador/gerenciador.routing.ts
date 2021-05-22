
import { Routes } from '@angular/router';
import { AtendimentoFormComponent } from './atendimento/atendimento-form/atendimento-form.component';
import { PainelAtendimentoFormComponent } from './atendimento/painel-atendimento-form/painel-atendimento-form.component';
import { AuthGuard } from 'app/_helpers/auth/auth.guard';

export const GerenciadorRoutes: Routes = [
   {
      path: 'painel/atendimento',
      children: [
         {
            path: '',
            component: AtendimentoFormComponent,
            canActivate: [AuthGuard],
            data: {
               role: 'ATENDIMENTO_CADASTRAR',
               title: 'Novo de Atendimento'
            }
         },
         {
            path: 'gerenciador',
            component: PainelAtendimentoFormComponent,
            canActivate: [AuthGuard],
            data: {
               role: 'ATENDIMENTO_CONSULTAR',
               title: 'Genenciador de Atendimento'
            }
         },
         {
            path: ':id',
            component: AtendimentoFormComponent, 
            canActivate: [AuthGuard],
            data: {
               role: 'ATENDIMENTO_EDITAR',
               title: 'Novo Atendimento'
            }
         },

      ]
   }
];
