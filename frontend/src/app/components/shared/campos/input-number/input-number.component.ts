import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { CamposBase } from '../campos-base';

@Component({
  selector: 'app-input-number',
  templateUrl: './input-number.component.html',
  styleUrls: ['./input-number.component.css']
})
export class InputNumberComponent extends CamposBase implements OnInit {

  @ViewChild('ComponentFocusIn') setaFoco: ElementRef;

  constructor() {
    super();
  }

  ngOnInit(): void {
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
