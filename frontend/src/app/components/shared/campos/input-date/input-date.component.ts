import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import * as moment from 'moment';
import { CamposBase } from '../campos-base';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
   selector: 'app-input-date',
   templateUrl: './input-date.component.html',
   styleUrls: ['./input-date.component.css']
})
export class InputDateComponent extends CamposBase implements OnInit {

   @Output() dateChange: EventEmitter<MatDatepickerInputEvent<any>> = new EventEmitter();;

   @Input() default = false;
   @Input() curdate = false;
   @Input() iniDateYear = false;


   minDate: Date;
   startDate = new Date();

   constructor() {
      super();
   }
   ngOnInit(): void {
      if (this.default) {
         this.formControl.setValue(moment());;
      }

      if (this.curdate) {
         this.minDate = new Date();
      }

      if (this.iniDateYear) {
         this.startDate = new Date(1970, 0, 1);
      }
   }

   getView(): string {
      if (this.iniDateYear) {
         return "multi-year"
      }
      return "month"
   }

   onChangedate() {
      this.dateChange.emit();
   }

}
