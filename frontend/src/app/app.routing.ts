import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { SiteLayoutComponent } from './layouts/site-layout/site-layout.component';
import { PaginaNaoEncontradaComponent } from './view/compartilhado/pagina-nao-encontrada/pagina-nao-encontrada.component';
import { LoginFormComponent } from './view/login-form/login-form.component';
import { AuthGuard } from './_helpers/auth/auth.guard';

const routes: Routes = [
   {
      path: '',
      redirectTo: 'site',
      pathMatch: 'full',
   },

   {
      path: 'login',
      component: LoginFormComponent,
   },
   {
      path: 'site',
      loadChildren: () => import('./site.module').then(m => m.SiteModule).catch(err => console.log('Falha Módulo Site!'))

   },
   {
      path: 'pagina-nao-encontrada',
      component: PaginaNaoEncontradaComponent,
   },
   {
      path: '',
      component: AdminLayoutComponent,
      canActivate: [AuthGuard],
      data: {
         role: 'SISTEMA_ACESSAR'
   },
    children: [
         {
            path: '',
            loadChildren: () => import('./layouts/admin-layout/admin-layout.module').then(m => m.AdminLayoutModule).catch(err => console.log('Falha Módulo Dashboard!'))
         },
         {
            path: '',
            loadChildren: () => import('./view/cadastro/cliente.module').then(m => m.ClienteModule).catch(err => console.log('Falha Módulo Cliente!'))
         },
         {
            path: '',
            loadChildren: () => import('./view/cadastro/produto.module').then(m => m.ProdutoModule).catch(err => console.log('Falha Módulo Produto!'))
         },
         {
            path: '',
            loadChildren: () => import('./view/cadastro/admin.module').then(m => m.AdminModule).catch(err => console.log('Falha Módulo admin!'))
         },
         {
            path: '',
            loadChildren: () => import('./view/gerenciador/gerenciador.module').then(m => m.GerenciadorModule).catch(err => console.log('Falha Módulo Gerencial!'))
         },
         // {
         //    path: '',
         //    loadChildren: () => import('./view/gerenciador/agenda/agenda.module').then(m => m.AgendaModule).catch(err => console.log('Falha Módulo Gerencial!'))
         // },
         
      ],

   },
   {
      path: '**',
      redirectTo: 'pagina-nao-encontrada',
      pathMatch: 'full',
   },
];

@NgModule({
   imports: [
      CommonModule,
      RouterModule.forRoot(routes)
   ],
   exports: [
   ],
})
export class AppRoutingModule { }
