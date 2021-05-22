import { Component, OnInit } from '@angular/core';
import { buttomFloatingAnimations } from './button-floating.animation';

@Component({
   selector: 'app-button-floating',
   templateUrl: './button-floating.component.html',
   styleUrls: ['./button-floating.component.scss'], 
   animations: buttomFloatingAnimations
})
export class ButtonFloatingComponent {

   fabButtons = [
      {
         icon: 'person_add'
      }
   ];
   buttons = [];
   fabTogglerState = 'inactive';

   constructor() { }

   showItems() {
      this.fabTogglerState = 'active';
      this.buttons = this.fabButtons;
   }

   hideItems() {
      this.fabTogglerState = 'inactive';
      this.buttons = [];
   }

   onToggleFab() {
      this.buttons.length ? this.hideItems() : this.showItems();
   }



}
