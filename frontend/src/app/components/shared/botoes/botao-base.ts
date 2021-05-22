import { EventEmitter, Input, Output, Directive } from "@angular/core";

@Directive()
export class BotaoBase {

    @Input() styles = 'btn-grid';
    @Output() cClick = new EventEmitter();
      
    onClick(){
      this.cClick.emit();
    }
}
