import { Component, OnInit, Input } from '@angular/core';
import { CamposBase } from '../campos-base';

@Component({
   selector: 'app-input-select',
   templateUrl: './input-select.component.html',
   styleUrls: ['./input-select.component.css']
})
export class InputSelectComponent extends CamposBase {

   @Input() opcoes: any[] = [];
   @Input() multi = false


   constructor() {
      super();
   }

   compareFn(o1: any, o2: any) {
      if (o1 == o2) {
         return true;
      } else { return false }
   }

}
