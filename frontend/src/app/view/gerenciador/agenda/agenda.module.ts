import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/view/compartilhado/shared.module';
import { AgendaComponent } from './agenda.component';
import { AgendaRoutes } from './agenda.routing';



@NgModule({
  declarations: [AgendaComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(AgendaRoutes),
    SharedModule

  ]
})
export class AgendaModule { }
