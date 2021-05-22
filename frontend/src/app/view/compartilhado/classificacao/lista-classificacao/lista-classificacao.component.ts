import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChange, SimpleChanges } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { ClassificacaoLista } from 'app/model/classificao/classificao';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { ClassificacaoOpcoes } from './../../../../model/classificao/classificao';
import { ClassificaoService } from './../../../../service/cadastro/classificao.service';


export const _filter = (opt: ClassificacaoOpcoes[], value: string): ClassificacaoOpcoes[] => {
   if (typeof value === 'object') { value = '' };
   const filterValue = value.toLowerCase();

   return opt.filter(item => item.descricaoCompleta.toLowerCase().includes(filterValue.toLowerCase()));
};


@Component({
   selector: 'app-lista-classificacao',
   templateUrl: './lista-classificacao.component.html',
   styleUrls: ['./lista-classificacao.component.scss']
})


export class ListaClassificacaoComponent implements OnInit, OnChanges {

   @Output() changeAtualizar = new EventEmitter;
   @Input() opcao: ClassificacaoOpcoes;

   control = new FormControl();
   options: ClassificacaoLista[]
   filteredOptions: Observable<ClassificacaoLista[]>;

   value: ClassificacaoOpcoes;

   constructor(
      private service: ClassificaoService,
      private fb: FormBuilder
   ) { }

   ngOnInit(): void {
      this.service.listarClassificacao().then((response) => {
         this.options = response;

         this.filteredOptions = this.control.valueChanges
            .pipe(
               startWith(''),
               map(value => this._filterGroup(value))
            );
      });
   }

   ngOnChanges(changes: SimpleChanges): void {
      const change: SimpleChange = changes.opcao;

      if (change.currentValue.id) {
         this.value = change.currentValue;
      }
   }

   private _filterGroup(value: string): ClassificacaoLista[] {
      if (value) {
         return this.options
            .map(group => ({ id: group.id, descricao: group.descricao, opcoes: _filter(group.opcoes, value) }))
            .filter(group => group.opcoes.length > 0);
      }

      return this.options;
   }


   display(item: any): string {
      if (item == undefined) { return }
      return item.descricaoCompleta;
   }

   onSelecionar(event) {
      this.opcao = event;
      this.changeAtualizar.emit(this.opcao);
   }

}
