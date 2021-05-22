import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { TransferHttpCacheModule } from '@nguniversal/common';
import { NgxSpinnerModule } from 'ngx-spinner';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
import { LoadingModule } from './components/loading/loading.module';
import { MaterialModule } from './components/shared/material/material.module';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { SistemaService } from './service/seguranca/sistema.service';
import { PaginaNaoEncontradaComponent } from './view/compartilhado/pagina-nao-encontrada/pagina-nao-encontrada.component';
import { LoginFormComponent } from './view/login-form/login-form.component';
import { SegurancaModule } from './view/seguranca/seguranca/seguranca.module';
import { HandlerModule } from './_helpers/errors/handler.module';


@NgModule({
   imports: [
      AppRoutingModule,
      BrowserModule.withServerTransition({ appId: 'serverApp' }),
      TransferHttpCacheModule,
      BrowserAnimationsModule,
      ComponentsModule,
      FormsModule,
      HttpClientModule,
      RouterModule,
      MaterialModule,
      SegurancaModule,
      NgxSpinnerModule,
      LoadingModule,
      HandlerModule, 
   ],
   exports: [
      LoginFormComponent
   ],
   declarations: [
      AppComponent,
      AdminLayoutComponent,
      LoginFormComponent,
      PaginaNaoEncontradaComponent,
   ],
   schemas: [
      CUSTOM_ELEMENTS_SCHEMA
   ],
   providers: [
      SistemaService,
   ],
   bootstrap: [AppComponent]
})
export class AppModule { }
