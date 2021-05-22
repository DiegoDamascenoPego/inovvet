import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChange, SimpleChanges } from '@angular/core';
import { CamposBase } from 'app/components/shared/campos/campos-base';
import { Seguimento } from './../../../model/seguimento/seguimento';
import { SeguimentoService } from './../../../service/cadastro/seguimento.service';
@Component({
   selector: 'app-seguimento',
   templateUrl: './seguimento.component.html',
})
export class SeguimentoComponent extends CamposBase implements OnInit, OnChanges {

   @Input() seguimento: Seguimento;

   lista: Seguimento[];
   value: Seguimento;

   constructor(
      private service: SeguimentoService
   ) {
      super();
   }

   ngOnInit() {
      this.service.listarTodos().then((response) => {
         this.lista = response;
      })
   }

   ngOnChanges(changes: SimpleChanges): void {
      const change: SimpleChange = changes.seguimento;
      if (change.currentValue) {
         this.value = change.currentValue;
      }
   }

   compareFn(o1: any, o2: any) {
     
      if (o1.id === o2.id) {
         return true;
      } else { return false }
   }
}
