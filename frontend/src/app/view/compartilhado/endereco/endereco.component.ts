import {
   Component,
   ElementRef,
   EventEmitter,
   Input,
   OnChanges,
   OnInit,
   Output,
   SimpleChange,
   SimpleChanges,
   ViewChild,
} from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import { EnumTipoEndereco } from "app/model/endereco/enum-tipo-enedereco.enum";
import { FunctionsUtil } from "app/model/util/functions-util";
import { MensagemService } from "app/util/mensagem.service";
import { EnumValues } from "enum-values";
import { Observable } from "rxjs";
import { map, startWith } from "rxjs/operators";
import { Cidade, Estado } from "./../../../model/endereco/cidade";
import { Endereco } from "./../../../model/endereco/endereco";
import { EnderecoService } from "./../../../service/cadastro/endereco.service";

@Component({
   selector: "app-endereco",
   templateUrl: "./endereco.component.html",
})
export class EnderecoComponent implements OnInit, OnChanges {

   @Input() form: FormGroup;
   @Input() formControlCep: FormControl;
   @Input() formControlCidade: FormControl;
   @Input() cadastroCompleto: boolean = true;
   @Output() changeEndereco = new EventEmitter();
   @ViewChild("focoNumero") focoNumero: ElementRef;

   estados: Estado[] = [];

   tipos = EnumValues.getNamesAndValues(EnumTipoEndereco);

   filtroCidade: Observable<Cidade[]>;
   valueCidade = "";

   public mask = [
      "(",
      /[1-9]/,
      /\d/,
      /\d/,
      ")",
      " ",
      /\d/,
      /\d/,
      /\d/,
      "-",
      /\d/,
      /\d/,
      /\d/,
      /\d/,
   ];

   constructor(
      private enderecoService: EnderecoService,
      private msg: MensagemService) { }

   ngOnInit(): void {
      this.enderecoService.listarUf().subscribe((response) => {
         this.estados = response;
         this.listarCidades({sigla: 'SP', nome: 'São Paulo'});
      });
   }

   ngOnChanges(changes: SimpleChanges): void {
      if (changes.cidade && changes.cidade.currentValue) {
         this.listarCidades(changes.cidade.currentValue.uf);
         this.selecionarCidadePadrao(changes.cidade.currentValue);
      }
   }

   listarCidades(value: Estado) {      
      this.valueCidade = "";
      this.enderecoService.listarCidade(value.sigla).subscribe((response) => {
         this.enderecoService.cidades = response;
         this.filtroCidade = this.formControlCidade.valueChanges.pipe(
            startWith<string | Cidade>(""),
            map((value) => (typeof value === "string" ? value : value.nome)),
            map((name) =>
               name ? this._filterCidade(name) : this.enderecoService.cidades.slice()
            )
         );
      });
   }

   private _filterCidade(value: any): Cidade[] {
      if (value) {
         return this.enderecoService.cidades.filter((item: any) => {
            if (typeof value === "object") {
               value = "";
            }
            const TempString = FunctionsUtil.removeAcentos(item.nome);

            return TempString.toLowerCase().includes(value.toLowerCase());
         });
      }
   }

   compareFnUF(o1: any, o2: any) {
      if (o1.sigla === o2.sigla) {
         return true;
      } else {
         return false;
      }
   }

   displayCidade(item: any): string {
      if (item == undefined) {
         return;
      }
      return item.nome;
   }

   buscarCep(event) {
      this.enderecoService.buscarCep(event).subscribe((response) => {
         this.listarCidades({nome: '', sigla: response.cidade.uf});
         this.changeEndereco.emit(response);
         if (response.cidade) {
            this.selecionarCidadePadrao(response.cidade);
         }

         setTimeout(() => {
            try {
               if (this.focoNumero.nativeElement.value == "") {
                  this.focoNumero.nativeElement.focus();
               }
            } catch (error) { }
         }, 900);
      },
         (response) => {
            this.msg.onMessage(response.error?.tipo, response.error?.titulo, response.error?.descricao);
         });
   }

   selecionarCidadePadrao(cidade) {
      if (cidade) {
         this.formControlCidade.setValue({ nome: cidade.nome });
      }
   }

   compareFn(o1: any, o2: any) {

      if (o1 == o2) {
         return true;
      } else { return false }
   }
}
