import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ButtonFloatingComponent } from './button-floating/button-floating.component';
import { ButtonFormComponent } from './button-form/button-form.component';
import { ButtonGridConfirmComponent } from './button-grid-confirm/button-grid-confirm.component';
import { ButtonGridDeleteComponent } from './button-grid-delete/button-grid-delete.component';
import { ButtonGridEditComponent } from './button-grid-edit/button-grid-edit.component';
import { ButtonGridComponent } from './button-grid/button-grid.component';
import { ButtonNextComponent } from './button-next/button-next.component';
import { ButtonSaveComponent } from './button-save/button-save.component';
import { ButtonComponent } from './button/button.component';



@NgModule({
  declarations: [
    ButtonSaveComponent,
    ButtonGridEditComponent,
    ButtonGridDeleteComponent,
    ButtonFormComponent,
    ButtonNextComponent,
    ButtonComponent,
    ButtonGridComponent,
    ButtonGridConfirmComponent,
    ButtonFloatingComponent, 
    
  ],
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
  ],
  exports: [
    ButtonSaveComponent,
    ButtonGridEditComponent,
    ButtonGridDeleteComponent,
    ButtonFormComponent,
    ButtonNextComponent,
    ButtonComponent,
    ButtonGridComponent,
    ButtonGridConfirmComponent,
    ButtonFloatingComponent
  ]
})
export class BotaoModule { }
