import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CamposBase } from '../campos-base';
import { EntityBase } from 'app/model/entity-base';

@Component({
  selector: 'app-input-select-entity-base',
  templateUrl: './input-select-entity-base.component.html',
  styleUrls: ['./input-select-entity-base.component.css']
})
export class InputSelectEntityBaseComponent extends CamposBase implements OnInit {

  @Input() opcoes: EntityBase[] = [];
  @Output() change = new EventEmitter();

  constructor() { 
    super();
  }

  ngOnInit(): void {
  }

  compareFn(o1: any, o2: any) {
    if (o1.id === o2.id) {
       return true;
    } else { return false }
  }

  onChange(){
    this.change.emit()
  }

}
