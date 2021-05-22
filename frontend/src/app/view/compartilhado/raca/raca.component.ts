import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChange, SimpleChanges, AfterViewInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Raca } from './../../../model/pet/raca';
import { TipoAnimal } from './../../../model/pet/tipo.enum';
import { AnimalService } from 'app/service/cadastro/animal.service';


@Component({
   selector: 'app-raca',
   templateUrl: './raca.component.html',
})

export class RacaComponent implements OnChanges {

   @Input() raca: Raca;
   @Output() changeAtualizar = new EventEmitter;
   form = new FormControl();
   filteredOptions: Observable<Raca[]>;
   value: Raca;

   constructor(
      private animalService: AnimalService
   ) { }

   filtrarRacas(raca: Raca) {
      this.animalService.listarRacas(raca.tipo).then(response => {
         this.animalService.racas = response;
         this.filteredOptions = this.form.valueChanges
            .pipe(
               startWith<string | Raca>(''),
               map(value => typeof value === 'string' ? value : value.descricao),
               map(name => name ? this._filter(name) : this.animalService.racas.slice())
            );

         this.form.setValue(raca);
      });
   }

   ngOnChanges(changes: SimpleChanges) {
      const racaChange: SimpleChange = changes.raca;

      if (racaChange.currentValue) {
         this.filtrarRacas(racaChange.currentValue);
      }
   }

   atualizar(entity: Raca) {
      this.value = entity
      this.raca = entity;
      this.changeAtualizar.emit(this.raca);
   }

   private _filter(value: any): Raca[] {
      if (value) {
         return this.animalService.racas.filter((item: any) => {
            if (typeof value === 'object') { value = '' };
            const TempString = item.descricao;
            return TempString.toLowerCase().includes(value.toLowerCase());
         });
      }
   }
   onSelecionar(event) {
      this.raca = event;

      this.changeAtualizar.emit(this.raca);
   }

   display(item: any): string {

      if (item == undefined) { return }
      return item.descricao;
   }
}
