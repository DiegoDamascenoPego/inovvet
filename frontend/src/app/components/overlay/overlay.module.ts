import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FocusBackModule } from 'app/_helpers/directive/focus-back/focus-back.module';
import { FocusTrapModule } from 'app/_helpers/directive/focus-trap/focus-trap.module';
import { OverlayComponent } from './overlay/overlay.component';



@NgModule({
  declarations: [OverlayComponent],
  imports: [
    CommonModule, 
    FocusBackModule,
    FocusTrapModule, 
    MatIconModule,
    MatButtonModule
  ]
})
export class OverlayModule { }
