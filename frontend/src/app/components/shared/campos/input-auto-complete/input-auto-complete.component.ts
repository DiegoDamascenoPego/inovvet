import { Component, OnInit, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { Icrud } from 'app/service/icrud';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged, tap, filter, switchMap } from 'rxjs/operators';

@Component({
   selector: 'app-input-auto-complete',
   templateUrl: './input-auto-complete.component.html',
   styleUrls: ['./input-auto-complete.component.css']
})
export class InputAutoCompleteComponent implements OnInit, OnChanges {

   @Output() changeAtualizar = new EventEmitter;
   @Output() changeAdicionar = new EventEmitter;
   @Input() service: Icrud;
   @Input() entity: any;
   @Input() value: any;

   @Input() formTitle: string;
   @Input() fControlName: string;
   @Input() hLabel= '';
   @Input() visibleAdd = false;


   control = new FormControl();
   filteredOptions: any[];

   constructor() { }

   ngOnChanges(changes: SimpleChanges): void {
      if(changes.entity){
         this.value = changes.entity.currentValue;
      }
   }

   ngOnInit(): void {

      this.control.valueChanges.pipe(
         debounceTime(500),
         distinctUntilChanged(),
         tap(() => {
            this.filteredOptions = [];
         }),
         filter(x => typeof x === 'string'),
         switchMap(value => this.service.listarPorNome(value)
            .pipe()
         )
      )
         .subscribe(data => {
            if (data == undefined) {
               this.filteredOptions = [];
            } else {
               this.filteredOptions = data;
            }
         });
   }

   display(item: any): string {
      if (item == undefined) { return }
      return item.nome;
   }

   onSelecionar(event) {
      this.entity = event;
      this.changeAtualizar.emit(this.entity);
   }

   clear(){
      this.value = '';
      this.entity = undefined;
      this.changeAtualizar.emit(this.entity);
   }

   onAdicionar(){
      this.changeAdicionar.emit();
   }

}
