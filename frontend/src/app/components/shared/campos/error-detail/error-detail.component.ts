import { Component, OnInit, Input } from '@angular/core';
import { InputValidatorsService } from '../input-validators.service';
import { FormGroup, AbstractControl } from '@angular/forms';

@Component({
   selector: 'app-error-detail',
   templateUrl: './error-detail.component.html',
   styleUrls: ['./error-detail.component.css']
})
export class ErrorDetailComponent {

   @Input() control: AbstractControl;

   constructor(
      public service: InputValidatorsService
   ) { }


}
