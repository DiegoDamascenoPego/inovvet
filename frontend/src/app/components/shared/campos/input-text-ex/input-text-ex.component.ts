import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ControlValueAccessor } from '@angular/forms';
import { CamposBase } from '../campos-base';

@Component({
   selector: 'app-input-text-ex',
   templateUrl: './input-text-ex.component.html',
   styleUrls: ['./input-text-ex.component.css',
   ]
})
export class InputTextExComponent extends CamposBase implements OnInit, ControlValueAccessor {

   @Input() public search = false;
   @Input() public value: string = null;
   @Output() public valueChange = new EventEmitter<string>();
   @Output() public EventSearch = new EventEmitter();

   public onChange = (value: string) => { };
   public onTouched = () => { }

   constructor() {
      super();
   }

   ngOnInit(): void {
   }

   public writeValue(value: string): void {
      this.value = value;
      this.onChange(this.value);
      this.valueChange.emit(this.value);
   }
   public registerOnChange(fn: (value: string) => void): void {
      this.onChange = fn;
   }

   public registerOnTouched(fn: ()=> void ): void {
      this.onTouched = fn;
   }

   public setDisabledState?(isDisabled: boolean): void {
      throw new Error('Method not implemented.');
   }
}
