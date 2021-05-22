import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChange, SimpleChanges } from '@angular/core';
import { Telefone } from './../../../model/telefone/telefone';
import { MatTableDataSource } from '@angular/material/table';



@Component({
   changeDetection: ChangeDetectionStrategy.OnPush,
   selector: 'app-telefone',
   templateUrl: './telefone.component.html',
   styleUrls: ['./telefone.component.scss']
})

export class TelefoneComponent implements OnInit, OnChanges {


   @Input() listaTelefones: Telefone[];
   @Output() changeTelefone = new EventEmitter;
   dataSource: MatTableDataSource<Telefone>;
   displayedColumns: string[] = ['tipo', 'numero', 'observacao', 'action'];
   telefones: Telefone[] = new Array();
   telefone: Telefone = new Telefone();
   constructor() {
      this.dataSource = new MatTableDataSource<Telefone>(this.telefones);
   }

   ngOnInit() {

   }

   ngOnChanges(changes: SimpleChanges): void {
      const telefoneChange: SimpleChange = changes.listaTelefones;
      if (telefoneChange) {
         this.telefones = telefoneChange.currentValue;
         this.dataSource = new MatTableDataSource<Telefone>(this.telefones);
      }
   }

   adicionar() {
      let bAdcionarnovo = true;
      this.telefones.forEach(fone => {
         if (fone.numero === this.telefone.numero) {
            bAdcionarnovo = false;
            fone.tipo = this.telefone.tipo;
            fone.numero = this.telefone.numero;
            fone.observacao = this.telefone.observacao;
         }
      });

      if (bAdcionarnovo) {
         this.telefones.push(new Telefone(this.telefone.tipo, this.telefone.numero, this.telefone.observacao));
      }

      this.telefone = new Telefone();
      this.onUpdate();
   }

   remover(telefone: Telefone) {
      this.telefones.forEach(fone => {
         if (fone.numero === telefone.numero) {
            this.telefones.splice(this.telefones.indexOf(fone), 1);
         }
      });

      this.onUpdate();
   }

   editar(telefone: Telefone) {
      this.telefone = telefone;

   }

   onUpdate() {
      this.dataSource = new MatTableDataSource<Telefone>(this.telefones);
      this.changeTelefone.emit(this.telefones);
   }

}
