import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-button-next',
  templateUrl: './button-next.component.html',
  styleUrls: ['./button-next.component.css']
})
export class ButtonNextComponent {

  @Input() formGroup: FormGroup;
  @Output() eClick = new EventEmitter();
  constructor() { }

  onClick() {
    this.eClick.emit();
  }
}
