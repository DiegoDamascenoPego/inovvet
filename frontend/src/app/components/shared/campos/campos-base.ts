import { Input, Output, EventEmitter, Directive } from "@angular/core";
import { AbstractControl, FormGroup, FormGroupName } from "@angular/forms";

@Directive()
export class CamposBase {

   @Input() formTitle: string;
   @Input() formGroup: FormGroup;
   @Input() formGroupName: FormGroupName;
   @Input() fControlName: string;
   @Input() fdisabled: boolean = false;;
   @Input() messageTooltip = '';
   @Input() hLabel = '';
   @Input() foco: boolean = false;
   @Output() Event = new EventEmitter;

   get formControl(): AbstractControl {
      if (this.formGroup.controls[this.fControlName] != null)
         return this.formGroup.controls[this.fControlName];
   }

}
