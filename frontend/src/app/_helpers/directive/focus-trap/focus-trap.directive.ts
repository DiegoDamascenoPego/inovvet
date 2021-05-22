import { AfterViewInit, Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
   selector: '[appFocusTrap]'
})
export class FocusTrapDirective implements AfterViewInit {

   private _lastFocusableElement: HTMLElement = null;

   private _firstFocusableElement: HTMLElement = null;

   constructor(private elementRef: ElementRef<any>) { }

   ngAfterViewInit(): void {
      const focusableElements = this.elementRef
         .nativeElement
         .querySelectorAll(`[tabindex]:not([tabindex="-1"]),
                           a[href]:not([disabled]),
                           a[mat-button]:not([true]),
                           button:not([disabled]),
                           textarea:not([disabled]),
                           input:not([disabled]),
                           select:not([disabled])`
         ) as Array<HTMLElement>;

      this._firstFocusableElement = focusableElements[0];
      this._lastFocusableElement = focusableElements[focusableElements.length - 1];
      this._firstFocusableElement.focus();
   }

   @HostListener('keydown', ['$event'])
   public manageTab(event: KeyboardEvent): void {
      if (event.key !== 'Tab') {
         return;
      }


      if (event.shiftKey && document.activeElement === this._firstFocusableElement) {
         this._lastFocusableElement.focus();
         event.preventDefault();
      } else if (document.activeElement === this._lastFocusableElement) {
         this._firstFocusableElement.focus();
         event.preventDefault();
      }
   }
}
