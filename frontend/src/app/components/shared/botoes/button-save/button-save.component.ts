import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-button-save',
  templateUrl: './button-save.component.html',
  styleUrls: ['./button-save.component.css']
})
export class ButtonSaveComponent {

  @Input() formGroup: FormGroup;
  @Input() label = 'Salvar';

  @Output() click = new EventEmitter();

  constructor() { }

  onClick(){
    this.click.emit();
  }

}
