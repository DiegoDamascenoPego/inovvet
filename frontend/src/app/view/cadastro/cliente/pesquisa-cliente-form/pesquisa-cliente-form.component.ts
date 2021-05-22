import { Component, Injector } from '@angular/core';
import { Cliente } from 'app/model/cliente/cliente';
import { TipoPessoa } from 'app/model/cliente/tipo-pessoa.enum';
import { Column, TypeField, TypeStyle, Columns, ColumnAction, ColumnMask } from 'app/components/shared/table/table/table.component';
import { ClienteFiltro } from './../../../../model/cliente/cliente';
import { PesquisaBase } from './../../../compartilhado/base/pesquisa-base';
import { ClienteService } from 'app/service/cadastro/cliente/cliente.service';


@Component({
  selector: 'app-pesquisa-cliente-form',
  templateUrl: './pesquisa-cliente-form.component.html',
})
export class PesquisaClienteFormComponent extends PesquisaBase<Cliente> {

  displayedColumns: Column[] = [
    new Columns("id", "Código", [TypeStyle.W100]),
    new Columns("nome", "Nome"),
    new ColumnMask("cpf", "CPF", '000.000.000-00'),
    new ColumnAction("action")];

  tituloPagina = 'Gerenciador de clientes';

  constructor(
    protected service: ClienteService, protected injector: Injector) {
    super(service, injector);
  }

  setForm() {
    this.form = this.fb.group({
      nome: [''],
      tipo: [TipoPessoa.Fisica],
      cpf: [''],
      ativo: [true]
    });
  }

  createFiltro() {
    this.filtro.obj = new ClienteFiltro();
    this.filtro.obj = this.form.value;
  }

  create(): Cliente {
    return new Cliente();
  }


}
