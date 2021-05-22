import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { BotaoBase } from '../botao-base';

@Component({
   selector: 'app-button-grid-delete',
   templateUrl: './button-grid-delete.component.html',
   styleUrls: ['./button-grid-delete.component.css']
})
export class ButtonGridDeleteComponent extends BotaoBase {

   constructor() {
      super();
   }



}
