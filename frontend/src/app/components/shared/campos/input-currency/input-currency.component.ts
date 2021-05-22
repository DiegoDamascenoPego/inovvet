import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { CamposBase } from '../campos-base';
import { debounceTime } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-input-currency',
  templateUrl: './input-currency.component.html',
  styleUrls: ['./input-currency.component.css']
})
export class InputCurrencyComponent extends CamposBase implements OnInit {

  @Output() change = new EventEmitter();
  @Input() info = '';
  @Input() prefix = '';
  @Input() suffix = '';

  private subject: Subject<string> = new Subject();


  constructor() {
    super();
  }

  ngOnInit(): void {
    this.subject.pipe(debounceTime(600)).subscribe(value => {      
      this.change.emit(this.formControl.value);
    });
  }

  changeValues() {
    this.subject.next(this.formControl.value);
  }

}
