import { Routes } from '@angular/router';
import { SiteLayoutComponent } from './layouts/site-layout/site-layout.component';
import { SiteGuard } from './service/seguranca/site.guard';
import { PaginaNaoEncontradaComponent } from './view/compartilhado/pagina-nao-encontrada/pagina-nao-encontrada.component';
import { SiteContaAtivarComponent } from './view/site/site-conta-ativar/site-conta-ativar.component';
import { SiteContaUsuarioAtivarComponent } from './view/site/site-conta-usuario-ativar/site-conta-usuario-ativar.component';
import { SiteContaComponent } from './view/site/site-conta/site-conta.component';
import { SiteFormPlanosComponent } from './view/site/site-form-planos/site-form-planos.component';
import { SiteVitrineComponent } from './view/site/site-vitrine/site-vitrine.component';

export const SiteRoutes: Routes = [
   {
      path: '',
      component: SiteLayoutComponent,
      children: [
         {
            path: '',
            component: SiteFormPlanosComponent,
         },
         {
            path: 'vitrine/:seller/:sku',
            component: SiteVitrineComponent,
            canActivate: [SiteGuard]
         },
         {
            path: 'conta',
            component: SiteContaComponent,
            canActivate: [SiteGuard],
         },
         {
            path: 'conta/ativar/:token',
            component: SiteContaAtivarComponent,
            canActivate: [SiteGuard],
         },
         {
            path: 'conta/usuario/ativar/:token',
            component: SiteContaUsuarioAtivarComponent,
            canActivate: [SiteGuard],
         }
         
         
      ]
   },
   {
      path: 'pagina-nao-encontrada',
      component: PaginaNaoEncontradaComponent,
   },   
   {
      path: '**',
      redirectTo: 'pagina-nao-encontrada',
      pathMatch: 'full',
   },
];

