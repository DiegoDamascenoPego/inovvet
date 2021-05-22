import { Component, OnInit } from '@angular/core';
import { OverlayConfig } from '../overlay-config';
import { OverlayRef } from '../overlay-ref';

@Component({
   selector: 'app-overlay',
   templateUrl: './overlay.component.html',
   styleUrls: ['./overlay.component.scss']
})
export class OverlayComponent {

   public config: OverlayConfig;
   public overlayRef: OverlayRef;

   constructor() { }

}
