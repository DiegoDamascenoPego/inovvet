import { Component, OnInit, Input, Output, EventEmitter, ChangeDetectionStrategy, OnChanges, SimpleChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
   changeDetection: ChangeDetectionStrategy.OnPush,
   selector: 'app-button-form',
   templateUrl: './button-form.component.html',
   styleUrls: ['./button-form.component.css']
})
export class ButtonFormComponent {

   @Input() formGroup: FormGroup;
   @Input() validStateForm: Boolean = true;
   @Input() isSave = false;

   @Output() save = new EventEmitter();
   @Output() next = new EventEmitter();

   constructor() { }


   onSave() {
      this.save.emit();
   }

   onNext() {
      this.next.emit();
   }

   onValid() {
      return this.validStateForm ? this.formGroup.valid : false;
   }

}
