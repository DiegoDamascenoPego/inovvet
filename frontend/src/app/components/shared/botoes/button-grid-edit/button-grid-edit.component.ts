import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { BotaoBase } from '../botao-base';

@Component({
  selector: 'app-button-grid-edit',
  templateUrl: './button-grid-edit.component.html',
  styleUrls: ['./button-grid-edit.component.css']
})
export class ButtonGridEditComponent extends BotaoBase {

  constructor() {
    super();
  }


}
