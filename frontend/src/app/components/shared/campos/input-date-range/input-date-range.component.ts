import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { CamposBase } from '../campos-base';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import * as moment from 'moment';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-input-date-range',
  templateUrl: './input-date-range.component.html',
  styleUrls: ['./input-date-range.component.css']
})
export class InputDateRangeComponent extends CamposBase implements OnInit {

  @Output() dateChange: EventEmitter<MatDatepickerInputEvent<any>> = new EventEmitter();;
  @Input() intervalo: FormGroup;


  minDate: Date;
  startDate = new Date();

  constructor() {
    super();
  }
  ngOnInit(): void {
    
  }

  onChangedate() {
    this.dateChange.emit();
  }

}
