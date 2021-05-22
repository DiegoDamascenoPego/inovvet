import { Component, OnInit, Input, Type, Output, EventEmitter } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { CurrencyPipe, DecimalPipe, PercentPipe, DatePipe } from '@angular/common';
import { PageEvent } from '@angular/material/paginator';
import { MaskPipe } from 'ngx-mask';
import { ValueConverter } from '@angular/compiler/src/render3/view/template';

export enum TypeField {
   TEXT,
   NUMBER,
   CURRENCY,
   PERCENT,
   DATETIME,
   ACTION,
   PHONE,
   STATUS
}

export enum TypeStyle {
   NONE,
   W75,
   W100,
   W120,
   W150,
   W200,
   W250,
   W300,
   MW250,
   W350,
   W400,
   WA400,
   TCENTER,
   TRIGHT,
   TLEFT
}

export enum TypeStatus {
   PENDENTE = ('PENDENTE'),
   ATRASADO = ('ATRASADO'),
   CONFIRMADO = ('CONFIRMADO'),
   CANCELADO = ('CANCELADO'),
   FINALIZADO = ('FINALIZADO')
}

export enum TypeAction {
   SUCCESS,
   EDIT,
   DELETE
}
export interface Column {
   definition: string;
   type: TypeField;
   get?(row: any, col: Column): string;
   getStatus?(row, col: Column, status: TypeStatus): boolean;
   header: string
   style?: TypeStyle[];
   canOrder?: boolean;
   mask?: string;
   action?: TypeAction[];
}


export class Columns implements Column {
   definition: string;
   type: TypeField;
   header: string;
   style?: TypeStyle[];
   canOrder?: boolean;
   mask?: string;
   action?: TypeAction[];

   constructor(definition: string, header: string, style?: TypeStyle[], type?: TypeField, canOrder?: boolean, mask?: string) {
      this.definition = definition;
      this.type = type || TypeField.TEXT;
      this.header = header;
      this.style = style;
      this.canOrder = canOrder;
      this.mask = mask;
   }
}

export class ColumnAction implements Column {
   definition: string;
   type: TypeField;
   header: string;
   style?: TypeStyle[];
   canOrder?: boolean;
   mask?: string;
   action?: TypeAction[];

   constructor(definition: string, action?: TypeAction[]) {
      this.definition = definition;
      this.type = TypeField.ACTION;
      this.style = [TypeStyle.NONE];
      this.header = "Ações";
      this.action = action || [TypeAction.EDIT, TypeAction.DELETE];
   }

}

export class ColumnStatus extends Columns {

   getStatus(row, col: Column, status: TypeStatus): boolean {
      const value: string = row[col.definition].toString().toUpperCase();
      return value == status.toString();
   }
}

export class ColumnEntity implements Column {
   definition: string;
   type: TypeField;
   header: string;
   style?: TypeStyle[];
   canOrder?: boolean;
   mask?: string;
   action?: TypeAction[];

   constructor(definition: string, header: string, style?: TypeStyle[], type?: TypeField, canOrder?: boolean, mask?: string) {
      this.definition = definition;
      this.type = type || TypeField.TEXT;
      this.header = header;
      this.style = style || [TypeStyle.W200];
      this.canOrder = canOrder;
      this.mask = mask;
   }

   get(row: any, col: Column): string {
      return row[col.definition].nome;
   }
}

export class ColumnMask implements Column {
   definition: string;
   type: TypeField;
   header: string;
   style?: TypeStyle[];
   canOrder?: boolean;
   mask?: string;
   action?: TypeAction[];

   constructor(definition: string, header: string, mask: string, style?: TypeStyle[]) {
      this.definition = definition;
      this.type = TypeField.TEXT;
      this.header = header;
      this.style = style;
      this.mask = mask;
   }
}

@Component({
   selector: 'app-table',
   templateUrl: './table.component.html',
   styleUrls: ['./table.component.css']
})

export class TableComponent implements OnInit {

   @Input() displayedColumns: Column[] = [];
   @Input() dataSource: MatTableDataSource<any>;
   @Input() card = 'card-body'
   @Input() pagelength = 0;
   @Input() pageIndex = 0;
   @Input() pageSize = 1;

   @Output() confirm = new EventEmitter();
   @Output() edit = new EventEmitter();
   @Output() delete = new EventEmitter();
   @Output() page = new EventEmitter();



   ENUMS = TypeField;
   ACTION = TypeAction;
   STATUS = TypeStatus;

   constructor(
      private decimalPipe: DecimalPipe,
      private currencyPipe: CurrencyPipe,
      private percentPipe: PercentPipe,
      private datePipe: DatePipe,
      private maskPipe: MaskPipe) { }

   ngOnInit(): void {
   }

   columnsProps(): string[] {
      return this.displayedColumns.map((column: Column) => column.definition);
   }

   transform(type: TypeField, value: any, mask: string): string {

      switch (type) {
         case TypeField.CURRENCY: return this.currencyPipe.transform(value, 'BRL', 'symbol', '1.2-2'); break;
         case TypeField.NUMBER: return this.decimalPipe.transform(value, '2'); break;
         case TypeField.PERCENT: return this.percentPipe.transform(value / 100, '2.2-2'); break;
         case TypeField.DATETIME: return this.datePipe.transform(value, 'dd/MM/yyyy hh:mm'); break;
         case TypeField.PHONE:
            if (value && (typeof value == 'string')){
               if (value.length == 10) {
                  return this.maskPipe.transform(value, '00 0000-0000');
               } else {
                  return this.maskPipe.transform(value, '00 00000-0000');
               }
            }
            break;
         case TypeField.STATUS: return ''; break;
         default:
            if (mask) {
               return this.maskPipe.transform(value, mask);
            }
            return value;
      }
   }

   columnsValue(row: any, col: Column): string {

      if (row[col.definition] instanceof Object) {
         return this.transform(col.type, col.get(row, col), col.mask);
      } else {
         return this.transform(col.type, row[col.definition], col.mask);
      }

   }

   columnsStatus(row: any, col: Column, status: TypeStatus): boolean {
      return col.getStatus(row, col, status)
   }

   buttonView(col: Column, action: TypeAction) {
      return col.action.indexOf(action) >= 0;
   }

   buttonStyle(col: Column): string {
      return col.action.length > 2 ? 'btn-grid-full' : 'btn-grid';
   }

   columnStyle(col: Column): string {
      let styles = '';

      if (col.style) {

         col.style.forEach((value) => {
            switch (value) {
               case TypeStyle.TCENTER: styles = styles + 'th-center '; break;
               case TypeStyle.TRIGHT: styles = styles + 'th-right '; break;
               case TypeStyle.TLEFT: styles = styles + 'th-left '; break;
               case TypeStyle.W75: styles = styles + 'w-75 '; break;
               case TypeStyle.W100: styles = styles + 'w-100 '; break;
               case TypeStyle.W120: styles = styles + 'w-120 '; break;
               case TypeStyle.W150: styles = styles + 'w-150 '; break;
               case TypeStyle.W200: styles = styles + 'w-200 '; break;
               case TypeStyle.W250: styles = styles + 'w-250 '; break;
               case TypeStyle.W300: styles = styles + 'w-300 '; break;
               case TypeStyle.W350: styles = styles + 'w-350 '; break;
               case TypeStyle.W400: styles = styles + 'w-400 '; break;
               case TypeStyle.WA400: styles = styles + 'wa-400 '; break;

               case TypeStyle.MW250: styles = styles + 'mw-250 '; break;
               default:
                  '';
            }
         })
      }

      if (col.type !== TypeField.TEXT) {
         styles = styles + 'th-center ';
      }

      if (col.type === TypeField.ACTION) {
         styles = styles + 'th-Action ';
      }

      return styles;
   }

   onEdit(row: any) {
      this.edit.emit(row);
   }

   onConfirm(row: any) {
      this.confirm.emit(row);
   }

   onDelete(row: any) {
      this.delete.emit(row);
   }

   onPaginar(event: PageEvent) {
      this.page.emit(event.pageIndex);
   }
}
