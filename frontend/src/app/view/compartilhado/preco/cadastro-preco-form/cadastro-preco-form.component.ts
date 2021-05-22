import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Preco, PrecoCalcular } from 'app/model/produto/preco';
import { PrecoService } from 'app/service/cadastro/preco.service';
import { Column, TypeField, TypeStyle, ColumnAction, Columns } from 'app/components/shared/table/table/table.component';
import { MensagemService } from 'app/util/mensagem.service';
import { LojaService } from 'app/service/cadastro/loja.service';
import { Loja } from 'app/model/Sistema/loja';


@Component({
   selector: 'app-cadastro-preco-form',
   templateUrl: './cadastro-preco-form.component.html',
})
export class CadastroPrecoFormComponent implements OnInit, OnChanges {

   @Input() precos: Preco[] = [];
   @Output() Change = new EventEmitter;

   formPreco: FormGroup;

   precoCalcular: PrecoCalcular;

   precoSelecionado: Preco;

   markup = 0;
   lucro = 0;
   loja: Loja;

   expanded = false;


   dataSource: MatTableDataSource<Preco>;
   displayedColumns: Column[] = [
      {
         definition: "loja", type: TypeField.TEXT,
         get(row: any, col: Column): string { return row[col.definition].nomeFantasia },
         header: "Loja", style: [TypeStyle.W250]
      },
      new Columns("despesaAdicional", "Desp. Adicional", [TypeStyle.W150], TypeField.CURRENCY),
      new Columns("despesaFixa", "Desp. Fixa", [TypeStyle.W150], TypeField.PERCENT),
      new Columns("despesaVariavel", "Desp. Variável", [TypeStyle.W150], TypeField.PERCENT),
      new Columns("custo", "Custo", [TypeStyle.W150], TypeField.CURRENCY),
      new Columns("margemLucro", "Marg. Lucro", [TypeStyle.W150], TypeField.PERCENT),
      new Columns("preco", "Preço", [TypeStyle.W150], TypeField.CURRENCY),
      new ColumnAction("action")];

   constructor(
      private fb: FormBuilder,
      private service: PrecoService,
      private msg: MensagemService, 
      lojaService: LojaService
   ) {
      lojaService.getLoja().subscribe(loja => this.loja = loja)
      this.setForm(new Preco(this.loja));
      
   }
   ngOnChanges(changes: SimpleChanges): void {
      if (changes.precos) {
         this.dataSource = new MatTableDataSource<Preco>(this.precos);
      }

      this.expanded = this.precos.length === 0;
   }

   ngOnInit(): void {
      this.dataSource = new MatTableDataSource<Preco>(this.precos);
      this.novoPreco();
   }

   setForm(entity: Preco) {
      this.formPreco = this.fb.group({
         loja: [entity.loja, [Validators.required]],
         custo: [entity.custo, [Validators.required, Validators.min(0)]],
         despesaAdicional: [entity.despesaAdicional, Validators.min(0)],
         despesaFixa: [entity.despesaFixa, Validators.min(0)],
         despesaVariavel: [entity.despesaVariavel, [Validators.min(0)]],
         margemLucro: [entity.margemLucro, [Validators.required, Validators.min(0.01)]],
         preco: [entity.preco, [Validators.required, Validators.min(0.01)]],
      });
   }

   novoPreco() {
      this.setForm(new Preco(this.loja));

      if (this.formPreco.controls['custo'].value === 0 && this.formPreco.controls['margemLucro'].value === 0) {
         this.service.novo().then(response => {
            this.formPreco.controls['despesaFixa'].setValue(response.despesaFixa);
            this.formPreco.controls['despesaVariavel'].setValue(response.despesaVariavel);
         });
      }
   }

   simularPreco(preco: number = 0) {
      if ((this.formPreco.controls['custo'].value > 0 && this.formPreco.controls['margemLucro'].value > 0) ||
         this.formPreco.controls['custo'].value > 0 && preco > 0) {
         this.precoCalcular = new PrecoCalcular();
         this.precoCalcular = this.formPreco.getRawValue() as PrecoCalcular;
         if (preco === 0) {
            this.precoCalcular.preco = 0;
         }

         this.service.calcular(this.precoCalcular).then(response => {
            this.formPreco.patchValue(response);
            this.markup = response.markup;
            this.lucro = response.valorLucro;
         }).catch((response) => {
            this.msg.onError(response.error.titulo, response.error.descricao);
         });
      }
   }

   atualizar(preco: number = 0) {
      this.simularPreco(this.formPreco.controls['preco'].value);
   }

   onSalvar() {
      if (this.formPreco.invalid) {
         return;
      }

      if (this.precoSelecionado) {
         const index = this.precos.indexOf(this.precoSelecionado);

         if (index > -1) {
            this.precos[index] = this.formPreco.getRawValue() as Preco;
         }

      } else {
         this.precos.push(this.formPreco.getRawValue() as Preco);
      }

      this.onUpdate();
      this.novoPreco();
      this.expanded = false;
      this.precoSelecionado = null;
   }

   onUpdate() {
      this.dataSource = new MatTableDataSource<Preco>(this.precos);
      this.Change.emit(this.precos);
   }


   getNameLoja(row: any, col: Column): string {
      return row[col.definition].nomeFantasia
   }

   onEdit(event: Preco) {
      this.precoSelecionado = event;
      this.setForm(event);
      this.simularPreco();
      this.expanded = true;
   }

   onDelete(event: Preco) {
      const index = this.precos.indexOf(event, 0)
      this.precos.splice(index, 1);
      this.onUpdate();
   }

   onExpanded(expanded = true) {
      this.expanded = expanded;
   }


}
