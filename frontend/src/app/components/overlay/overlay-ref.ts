import { ComponentRef } from "@angular/core";
import { OverlayComponent } from "./overlay/overlay.component";


export class OverlayRef {

   constructor(private componentRef: ComponentRef<OverlayComponent>) { }

   public close() {
      this.componentRef.destroy();
   }
}
