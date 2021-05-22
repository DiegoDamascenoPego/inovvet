import { Component, EventEmitter, Input, Output, OnChanges, SimpleChanges } from '@angular/core';
import { Vaga } from 'app/model/atendimento/vaga';
import { CamposBase } from '../campos-base';

@Component({
   selector: 'app-input-select-vaga',
   templateUrl: './input-select-vaga.component.html',
   styleUrls: ['./input-select-vaga.component.css']
})
export class InputSelectVagaComponent extends CamposBase {

   @Input() opcoes: Vaga[] = [];
   @Output() selected = new EventEmitter();

   constructor() {
      super();
   }
   
   compareFn(o1: any, o2: any) {
      if (o1.data) {
         if (o1.data === o2.data && o1.hora == o2.hora) {
            return true;
         } else { return false }
      } else {
         return false
      }

   }

   onSelect(event) {
      if (event.isUserInput) {
         this.selected.emit(event.source.value);
      }
   }

}
