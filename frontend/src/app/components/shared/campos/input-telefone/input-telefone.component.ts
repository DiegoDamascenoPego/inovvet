import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { CamposBase } from '../campos-base';

@Component({
   selector: 'app-input-telefone',
   templateUrl: './input-telefone.component.html',
   styleUrls: ['./input-telefone.component.css']
})
export class InputTelefoneComponent extends CamposBase {

   @Input() cText: string;
   @ViewChild('ComponentFocusIn') setaFoco: ElementRef;

   cMask = '(00)00000-0000';

   constructor() {
      super();
   }

   ngOnInit(): void {
      if (this.foco) {
         setTimeout(() => {
            try {
               this.setaFoco.nativeElement.focus();
            } catch (error) {

            }
         }, 900);
      }

      this.formControl.valueChanges.subscribe(value => {
         if (String(this.formControl.value).length == 3) {
            if (String(this.formControl.value).slice(-1) == '9') {
               this.cMask = '(00)00000-0000';
            } else {
               this.cMask = '(00)0000-0000';
            }
         }
      });

   }
}
