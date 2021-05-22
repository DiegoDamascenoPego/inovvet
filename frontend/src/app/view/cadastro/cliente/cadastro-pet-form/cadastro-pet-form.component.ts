import { Component, EventEmitter, Input, OnInit, Output, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Cliente } from 'app/model/cliente/cliente';
import { Raca } from 'app/model/pet/raca';
import { AnimalService } from 'app/service/cadastro/animal.service';
import { MensagemService } from 'app/util/mensagem.service';
import { EnumValues } from 'enum-values';
import { Animal, Cor } from './../../../../model/pet/animal';
import { TipoAnimal } from './../../../../model/pet/tipo.enum';
import * as moment from 'moment';
import { FunctionsUtil } from 'app/model/util/functions-util';


@Component({
   selector: 'app-cadastro-pet-form',
   templateUrl: './cadastro-pet-form.component.html',
   styleUrls: ['./cadastro-pet-form.component.scss']
})
export class CadastroPetFormComponent implements OnInit {

   @Output() closeCadastro = new EventEmitter;
   @Input() clienteSelecionado: Cliente;
   @ViewChild('setaFoco') foco: ElementRef;

   isCadastro: Boolean = false;
   raca: Raca;
   cores: Cor[];
   tipos = EnumValues.getNamesAndValues(TipoAnimal);

   controlCor = new FormControl();
   form: FormGroup;

   animal: Animal = new Animal();

   constructor(
      private route: ActivatedRoute,
      private fb: FormBuilder,
      private animalService: AnimalService,
      private mensagemService: MensagemService,
   ) {
      this.setForm();
   }

   setForm() {
      this.form = this.fb.group({
         id: [{ value: null, disabled: true }],
         tipo: [TipoAnimal.Cachorro, Validators.required],
         nome: ['', Validators.required],
         sexo: ['MASCULINO'],
         pelo: ['CURTO'],
         castrado: [0],
         dataNascimento: [],
         cores: [],
         raca: [],
         ativo: [true]
      });

      this.controlCor.patchValue({});
   }

   setAnimal(response) {
      this.animal = response;
      this.animal.dataNascimento = FunctionsUtil.dateToString(this.animal.dataNascimento);
      this.form.patchValue(this.animal);
      this.raca = this.animal.raca;
      this.controlCor.patchValue(this.animal.cores);
   }

   formatLabel(value: number | null) {
      if (!value) {
         return 0;
      }

      if (value >= 1000) {
         return Math.round(value / 1000) + 'kg'
      }

      return value;
   }

   ngOnInit() {
      this.animalService.listarCores().subscribe(
         (response) => {
            this.cores = response;
         }
      );
   }


   show(id) {
      this.isCadastro = true;
      if (id > 0) {
         this.carregar(id);
      } else {
         this.animal = new Animal();
         this.raca = new Raca();
         this.setForm();
         setTimeout(() => this.foco.nativeElement.focus());
      }
   }

   hide() {
      if (this.isCadastro) {
         this.isCadastro = false;
      } else {
         this.onVoltar();
      }
   }

   isPet(): boolean {
      return this.form.get('tipo').value === 'CACHORRO' ||
         this.form.get('tipo').value === 'GATO' ||
         this.form.get('tipo').value === 'COELHO' ? true : false;
   }

   carregar(id) {
      if (id > 0) {
         this.animalService.buscarPorId(id).subscribe((response) => {
            this.setAnimal(response);
         },
            (response) => {
               this.mensagemService.onError(response.error.titulo, response.error.detalhe);
            });
      }
   }

   salvar() {
      this.animal = this.form.value;
      this.animal.id = this.form.get('id').value;
      this.animal.clienteId = this.clienteSelecionado.id;
      this.animal.dataNascimento = moment(this.animal.dataNascimento).format('DD/MM/YYYY');
      this.animal.raca = this.raca;

      this.animal.cores = new Array();
      if (this.controlCor.value) {
         this.controlCor.value.forEach(cor => {
            this.animal.cores.push(cor);
         });
      }

      this.animalService.salvar(this.animal).then(
         (response) => {
            this.setAnimal(response);
            this.mensagemService.onSucess('Sucesso', 'Animal salvo.');
            this.isCadastro = false;
         }
      ).catch((response) => {
         this.mensagemService.onError(response.error.titulo, response.error.descricao);
      });
   }

   changeTipoAnimal(event) {
      if (event) {
         if (event.value !== this.raca.tipo) {
            this.raca = new Raca(event.value);
         }
      }
   }

   changeRaca(event) {
      if (event) {
         this.raca = event;
      }
   }

   onRemover() {
      this.animalService.apagar(this.animal.id).then(
         (response) => {
            this.form.reset();
            this.animal = new Animal();
            this.form.patchValue(this.animal);
            this.raca = new Raca();
            this.mensagemService.onSucess('Sucesso', 'Animal removido.');
            this.foco.nativeElement.focus();
         }).catch((response) => {
            this.mensagemService.onError(response.error.titulo, response.error.detalhe);
         }

         );

   }

   onVoltar() {
      this.closeCadastro.emit();
   }
}
