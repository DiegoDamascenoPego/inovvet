import { Component, OnInit, Input } from '@angular/core';
import { CamposBase } from '../campos-base';
import { Loja } from 'app/model/Sistema/loja';
import { LojaService } from 'app/service/cadastro/loja.service';

@Component({
   selector: 'app-input-select-loja',
   templateUrl: './input-select-loja.component.html',
   styleUrls: ['./input-select-loja.component.css']
})
export class InputSelectLojaComponent extends CamposBase {

   lojas: Loja[];

   constructor(
      lojaService: LojaService
   ) {
      super();
      lojaService.getLojas().subscribe(lojas => this.lojas = lojas)
   }

   compareFn(o1: Loja, o2: Loja) {

      if (o1.token === o2.token) {
         return true;
      } else { return false }
   }
}
