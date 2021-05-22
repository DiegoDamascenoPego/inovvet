import { AfterViewInit, Component, Injector, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PesquisaBase } from 'app/view/compartilhado/base/pesquisa-base';
import * as moment from 'moment';
import { EnumStatusAtendimento } from 'app/model/atendimento/enum-status-atendimento.enum';
import { EnumValues } from 'enum-values';
import { Column, TypeField, TypeStyle, TypeStatus, Columns, ColumnStatus, ColumnAction, TypeAction, ColumnEntity } from 'app/components/shared/table/table/table.component';
import { Atendimento, AtendimentoFiltro } from 'app/model/atendimento/atendimento';
import { AtendimentoService } from 'app/service/gerenciador/atendimento.service';
import { EntityBase } from 'app/model/entity-base';
import { FormGroup, FormControl } from '@angular/forms';
import { FunctionsUtil } from 'app/model/util/functions-util';
import { TipoAtendimento } from 'app/model/atendimento/tipo-atendimento';
import { TipoAtendimentoService } from 'app/service/cadastro/tipo-atendimento.service';
import { DialogComponent } from 'app/view/compartilhado/modal/dialog/dialog.component';
import { response } from 'express';
import { ClienteService } from 'app/service/cadastro/cliente/cliente.service';

export interface Agendamento {
   nome: string;
   animal: string;
   telefone: string;
   data: string;
   status: string;
}

@Component({
   selector: 'app-painel-atendimento',
   templateUrl: './painel-atendimento-form.component.html',
   styleUrls: ['./painel-atendimento-form.component.scss']
})
export class PainelAtendimentoFormComponent extends PesquisaBase<Atendimento> implements OnInit, AfterViewInit {

   @ViewChild(MatSort) sort: MatSort;

   showAtendimento = true;

   dataSource: MatTableDataSource<Atendimento>;
   intervalo: FormGroup;

   cliente: EntityBase;

   displayedColumns: Column[] = [
      new ColumnStatus("status", "Situação", [TypeStyle.W100, TypeStyle.TCENTER], TypeField.STATUS),
      // new Columns("id", "Código", [TypeStyle.W100]),
      {
         definition: "cliente", type: TypeField.TEXT, header: "Nome",
         get(row: any, col: Column): string { return row[col.definition].nome },
      },
      {
         definition: "animal", type: TypeField.TEXT, header: "Animal", style: [TypeStyle.W200],
         get(row: any, col: Column): string { return row[col.definition].nome },
      },

      new Columns("telefones", "Telefones", [TypeStyle.W150], TypeField.PHONE),
      new Columns("data", "Data", [TypeStyle.W200, TypeStyle.TCENTER], TypeField.DATETIME),
      new ColumnAction("action", [TypeAction.SUCCESS, TypeAction.EDIT, TypeAction.DELETE])
   ];

   atendimento: Atendimento[] = [];

   status = EnumValues.getNamesAndValues(EnumStatusAtendimento);
   tipoAtendimentos: TipoAtendimento[] = [];

   filtrar = false;

   constructor(
      protected service: AtendimentoService,
      protected injector: Injector,
      public clienteService: ClienteService,
      private tipoAtendimentoService: TipoAtendimentoService,) {
      super(service, injector);
      this.dataSource = new MatTableDataSource(this.atendimento);
      this.setForm()
   }

   ngOnInit() {
      this.tipoAtendimentoService.listarTodos().then((response) => {
         this.tipoAtendimentos = response;
      });
   }

   ngAfterViewInit() {
      // this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
   }

   createFiltro() {

      let atendimento: AtendimentoFiltro = new AtendimentoFiltro();
      atendimento = this.form.getRawValue() as AtendimentoFiltro;
      atendimento.cliente = this.cliente;
      atendimento.dataInicio = FunctionsUtil.dateToFormat(this.intervalo.value.inicio);
      atendimento.dataFim = FunctionsUtil.dateToFormat(this.intervalo.value.fim);
      this.filtro.obj = atendimento;
   }

   create(): Atendimento {
      return new Atendimento();
   }

   setForm() {
      this.intervalo = new FormGroup({
         inicio: new FormControl(new Date()),
         fim: new FormControl(new Date()),
      });

      this.form = this.fb.group({
         status: [[EnumStatusAtendimento.Pendente]],
         tipoAtendimento: [null]
      });
   }

   getStatus(entity: Agendamento): string {
      if (entity.status === 'pendente') {
         if (moment(entity.data).isBefore(moment())) {
            return 'atrasado';
         }
      }
      return entity.status;
   }

   onFiltrar() {
      this.filtrar = !this.filtrar;
   }

   onImprimir() {
      this.service.relatorioAtendimento().subscribe((response)=>{
      const report = new Blob([response], { type: 'application/pdf' });
      const pdf = URL.createObjectURL(new Blob([report], { type: 'application/pdf' }));
      window.open(pdf);
      });
      
   }

   onAdicionarCliente(event) {
      if (event) {
         this.cliente = event;
      } else {
         this.cliente = undefined;
      }
   }
   
   onNovoAtendimento() {
      this.router.navigate(['painel/atendimento'])
   }

   onCloseAtendimento(event) {
      this.showAtendimento = event;
   }


   public getDescricao(event) {       
      return event.cliente.nome + ' ' + FunctionsUtil.dateToFormat(event.data, 'DD/MM/YYYY hh:mm');
   }

   onFinalizar(event) {

      const dialogRef = this.dialog.open(DialogComponent, {
         data: { id: event.id, descricao:  this.getDescricao(event), titulo: 'Finalizar Atendimento' }
      });

      dialogRef.afterClosed().subscribe(result => {
         if (result && result > 0) {
            this.spinner.show();
            this.service.finalizar(event.id).subscribe(
               (response) => {
                  this.msg.onSucess('Sucesso', 'Atendimento Finalizado');
               },
               (response) => {
                  this.msg.onMessage(response.error.tipo, response.error.titulo, response.error.descricao);
               }

            )
         }
      });
   }
}

