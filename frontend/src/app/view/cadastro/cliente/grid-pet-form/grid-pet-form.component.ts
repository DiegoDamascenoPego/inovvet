import { Component, EventEmitter, Input, OnInit, Output, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';
import { Cliente } from 'app/model/cliente/cliente';
import { FiltroUtil } from 'app/model/util/filtro-util';
import { TipoAnimal } from '../../../../model/pet/tipo.enum';
import { AnimalService } from './../../../../service/cadastro/animal.service';

@Component({
  selector: 'app-grid-pet-form',
  templateUrl: './grid-pet-form.component.html',
  styleUrls: ['./grid-pet-form.component.scss']
})
export class GridPetFormComponent implements OnInit, OnChanges {


  @Output() onShowPet = new EventEmitter;
  @Input() clienteSelecionado: Cliente;
  @Input() isCadastro: boolean;

  animais: any[];
  filtro: FiltroUtil = new FiltroUtil();

  constructor(
    private service: AnimalService,
  ) { }

  ngOnInit() {
    this.carregarLista(this.clienteSelecionado.id);
  }

  ngOnChanges(changes: SimpleChanges): void {
    const show: SimpleChange = changes.isCadastro;

    if (!show) {
      this.carregarLista(this.clienteSelecionado.id);
    }
  }

  carregarLista(codigo) {
    this.filtro.obj = { cliente: { id: codigo } }
    this.filtro.page = 0;

    this.service.listarAnimais(this.filtro).then(response => {
      this.animais = response.content
    });
  }

  show(id: number) {
    this.onShowPet.emit(id);
  }

  getEspecie(especie: TipoAnimal) {
    return ''
  }

}
