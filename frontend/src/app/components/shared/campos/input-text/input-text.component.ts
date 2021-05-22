import { Component, ElementRef, OnInit, ViewChild, OnChanges, SimpleChanges, Output, EventEmitter, Input } from '@angular/core';
import { CamposBase } from '../campos-base';

@Component({
	selector: 'app-input-text',
	templateUrl: './input-text.component.html',
})
export class InputTextComponent extends CamposBase implements OnInit, OnChanges {

	@ViewChild('ComponentFocusIn') setaFoco: ElementRef;
	@Output() EventSearch = new EventEmitter();
	@Input() search = false;



	constructor() {
		super();
	}
	
	ngOnInit(): void {
		this.onSetaFoco();
	}

	ngOnChanges(changes: SimpleChanges): void {
		if(changes){
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
