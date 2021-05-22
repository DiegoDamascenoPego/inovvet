import { FabricanteService } from './../../../../service/cadastro/fabricante.service';
import { SimpleEntity, EntityBase } from './../../../../model/entity-base';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild, OnDestroy, AfterViewInit, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { debounceTime, switchMap, tap, distinctUntilChanged, filter } from 'rxjs/operators';
import { MatAutocompleteTrigger } from '@angular/material/autocomplete';

@Component({
   selector: 'app-pesquisa-marca',
   templateUrl: './pesquisa-marca.component.html',
   styleUrls: ['./pesquisa-marca.component.css']
})
export class PesquisaMarcaComponent implements OnInit, OnChanges, AfterViewInit, OnDestroy {

   @Input() marca: EntityBase;
   @Output() changeAtualizar = new EventEmitter;
   @ViewChild(MatAutocompleteTrigger) trigger: MatAutocompleteTrigger;

   subscription: Subscription;
   form = new FormControl();
   filteredOptions: EntityBase[];
   value: EntityBase;
   show = false;

   constructor(
      private service: FabricanteService,
      private fb: FormBuilder,
   ) { }

   ngOnInit(): void {
      this.form.valueChanges.pipe(
         debounceTime(500),
         distinctUntilChanged(),
         tap(() => {
            this.filteredOptions = [];
         }),
         filter(x => typeof x === 'string'),
         switchMap(value => this.service.pesquisarMarca(value)
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

   ngOnChanges(changes: SimpleChanges): void {
      const change: SimpleChange = changes.marca;

      if (change.currentValue && change.currentValue.id) {
         this.value = change.currentValue;
      }
   }

   ngAfterViewInit() {
      this._subscribeToClosingActions();
   }

   ngOnDestroy() {
      if (this.subscription && !this.subscription.closed) {
         this.subscription.unsubscribe();
      }
   }

   private _subscribeToClosingActions(): void {
      if (this.subscription && !this.subscription.closed) {
         this.subscription.unsubscribe();
      }

      this.subscription = this.trigger.panelClosingActions
         .subscribe(e => {
            if (!e || !e.source) {
               this.form.setValue(null);
            }
         },
            err => this._subscribeToClosingActions(),
            () => this._subscribeToClosingActions());
   }

   display(item: any): string {
      if (item == undefined) { return }
      return item.nome;
   }

   onAtualizar(event) {
      this.changeAtualizar.emit(event);
   }
}
