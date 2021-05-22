import { Component, OnInit } from '@angular/core';
import { BotaoBase } from '../botao-base';

@Component({
  selector: 'app-button-grid-confirm',
  templateUrl: './button-grid-confirm.component.html',
  styleUrls: ['./button-grid-confirm.component.css']
})
export class ButtonGridConfirmComponent extends BotaoBase{

  constructor() { 
    super();
  }

}
