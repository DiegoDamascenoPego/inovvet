import { Routes } from "@angular/router";
import { AuthGuard } from "app/_helpers/auth/auth.guard";
import { CadastroLojaFormComponent } from "./administrativo/cadastro-loja-form/cadastro-loja-form.component";
import { PesquisaLojaFormComponent } from "./administrativo/pesquisa-loja-form/pesquisa-loja-form.component";

export const AdminRoutes: Routes = [
   {
      path: 'cadastro/loja',
      children: [
         {
            path: '',
            component: CadastroLojaFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'LOJA_CADASTRAR',
            }
         },
         {
            path: 'gerenciador',
            component: PesquisaLojaFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'LOJA_CONSULTAR',
            }
         },
         {
            path: ':id',
            component: CadastroLojaFormComponent, canActivate: [AuthGuard],
            data: {
               role: 'LOJA_EDITAR',
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