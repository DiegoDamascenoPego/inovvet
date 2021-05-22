import { Routes } from "@angular/router";
import { AuthGuard } from "app/_helpers/auth/auth.guard";
import { AgendaComponent } from "./agenda.component";

export const AgendaRoutes: Routes = [
   {
      path: 'painel/agenda',
      children: [
         {
            path: '',
            component: AgendaComponent, canActivate: [AuthGuard],
            data: {
               role: 'TIPOATENDIMENTO_CADASTRAR',
               title: 'Agenda'
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
