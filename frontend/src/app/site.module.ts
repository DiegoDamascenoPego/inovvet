import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgxSpinnerModule } from 'ngx-spinner';
import { ComponentsModule } from './components/components.module';
import { SiteLayoutComponent } from './layouts/site-layout/site-layout.component';
import { MaterialModule } from './components/shared/material/material.module';
import { PipeModule } from './components/shared/pipe.module';
import { SiteRoutes } from './site.routing';
import { SharedModule } from './view/compartilhado/shared.module';
import { SiteAvisoComponent } from './view/site/site-aviso/site-aviso.component';
import { SiteContaAtivarComponent } from './view/site/site-conta-ativar/site-conta-ativar.component';
import { SiteContaTermoAceiteComponent } from './view/site/site-conta-termo-aceite/site-conta-termo-aceite.component';
import { SiteContaUsuarioAtivarComponent } from './view/site/site-conta-usuario-ativar/site-conta-usuario-ativar.component';
import { SiteContaComponent } from './view/site/site-conta/site-conta.component';
import { SiteFormPlanosComponent } from './view/site/site-form-planos/site-form-planos.component';
import { SiteVitrineComponent } from './view/site/site-vitrine/site-vitrine.component';
import { BotaoModule } from './components/shared/botoes/botao.module';
import { CamposModule } from './components/shared/campos/campos.module';


@NgModule({
   declarations: [
      SiteVitrineComponent,
      SiteLayoutComponent,
      SiteFormPlanosComponent,
      SiteContaComponent,
      SiteContaAtivarComponent,
      SiteAvisoComponent,
      SiteContaUsuarioAtivarComponent,
      SiteContaTermoAceiteComponent,
   ],
   imports: [
      FormsModule,
      ReactiveFormsModule,
      CommonModule,
      RouterModule.forChild(SiteRoutes),
      ComponentsModule,
      PipeModule,
      MaterialModule,
      CamposModule,
      BotaoModule,
      SharedModule,
      NgxSpinnerModule
   ],
   exports: [
      SiteLayoutComponent
   ],

})
export class SiteModule { }
