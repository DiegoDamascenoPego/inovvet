import { Directive } from '@angular/core';

@Directive({
   selector: '[appFocusBack]'
})
export class FocusBackDirective {

   private _lastFocusedElement: Element;

   constructor() { }

   ngOnInit(): void {
      this._lastFocusedElement = document.activeElement;
   }

   ngOnDestroy(): void {
      if (this._lastFocusedElement) {
         (this._lastFocusedElement as HTMLElement).focus()
      }
   }

}
