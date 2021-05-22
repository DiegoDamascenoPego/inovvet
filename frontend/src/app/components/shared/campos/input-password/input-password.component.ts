import { Component, ElementRef, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { CamposBase } from '../campos-base';

@Component({
   selector: 'app-input-password',
   templateUrl: './input-password.component.html',
})
export class InputPasswordComponent extends CamposBase implements OnInit, OnChanges {

   viewPs = false;

   @ViewChild('ComponentFocusIn') setaFoco: ElementRef;

   constructor() {
      super()
   }
   ngOnInit(): void {
      this.onSetaFoco();
   }


   ngOnChanges(changes: SimpleChanges): void {
      if (changes) {
         this.onSetaFoco();
      }
   }

   onSetaFoco() {
      if (this.foco) {
         setTimeout(() => {
            try {
               if (this.setaFoco.nativeElement.value == '') {
                  this.setaFoco.nativeElement.focus()
               }
            } catch (error) {

            }
         }, 900);
      }

   }
}