import { Component, OnInit, Input, ElementRef, ViewChild, OnChanges, SimpleChanges, EventEmitter, Output } from '@angular/core';
import { CamposBase } from '../campos-base';

@Component({
   selector: 'app-input-mask',
   templateUrl: './input-mask.component.html',
   styleUrls: ['./input-mask.component.css']
})
export class InputMaskComponent extends CamposBase implements OnChanges {

   @Input() cText: string;
   @Input() cMask = '';
   @Input() search = false;
   @Output() EventSearch = new EventEmitter();
   @ViewChild('ComponentFocusIn') setaFoco: ElementRef;
	

   constructor() {
      super();
   }

   ngOnInit(): void {
		this.onSetaFoco();
	}

	ngOnChanges(changes: SimpleChanges): void {
		if(changes.foco){
			this.onSetaFoco();
		}
	}

	onSetaFoco(){
		if (this.foco) {
			setTimeout(() => {
				try {
					if (this.setaFoco.nativeElement.value == '') {
						this.setaFoco.nativeElement.focus()
					}
				} catch (error) {

				}
			}, 900);
		}
	}

	onSearch(value){
		this.EventSearch.emit(value);
	}

}
