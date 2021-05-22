import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { RouterModule } from '@angular/router';
import { LoadingModule } from 'app/components/loading/loading.module';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { AdminLayoutRoutes } from './admin-layout.routing';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
  ],
  declarations: [
    DashboardComponent,
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' },
  ],
})

export class AdminLayoutModule { }
