import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CamposBase } from '../campos-base';
import { SimpleEntity } from 'app/model/entity-base';

@Component({
   selector: 'app-input-select-entity',
   templateUrl: './input-select-entity.component.html',
   styleUrls: ['./input-select-entity.component.css']
})
export class InputSelectEntityComponent extends CamposBase {

   @Input() opcoes: SimpleEntity[] = [];
   @Output() selected = new EventEmitter();

   constructor() {
      super();
   }

   compareFn(o1: any, o2: any) {
      if (o1.id === o2.id) {
         return true;
      } else { return false }
   }

   onSelect(event) {
      if (event.isUserInput) {
         this.selected.emit(event.source.value);
      }
   }
}
