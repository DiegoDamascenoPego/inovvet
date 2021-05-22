import { Component, EventEmitter, HostListener, OnInit, Output, Input } from '@angular/core';

@Component({
   selector: 'app-overlay',
   templateUrl: './overlay.component.html',
   styleUrls: ['./overlay.component.scss']
})
export class OverlayComponent implements OnInit {


   @Input() show = true;
   @Output() private showChange = new EventEmitter();

   ngOnInit(): void {

   }

   // tslint:disable-next-line:no-output-on-prefix
   @Output() onClose: EventEmitter<any> = new EventEmitter();

   desktop: boolean;

   constructor() {
      this.desktop = true;
   }

   @HostListener('window:keydown.esc')
   close() {
      this.show = false;
      this.showChange.emit(this.show);
      this.onClose.emit();
   }
}
