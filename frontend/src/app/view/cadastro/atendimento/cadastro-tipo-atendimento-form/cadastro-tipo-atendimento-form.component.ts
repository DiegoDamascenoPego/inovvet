import { DatePipe } from '@angular/common';
import { Component, Injector, Directive } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { EnumDiaSemana } from 'app/model/atendimento/enum-dia-semana.enum';
import { Tempo } from 'app/model/atendimento/enum-frequencia.enum';
import { TipoAtendimento } from 'app/model/atendimento/tipo-atendimento';
import { Horario, TipoAtendimentoAgenda } from 'app/model/atendimento/tipo-atendimento-agenda';
import { Produto } from 'app/model/produto/produto';
import { ProdutoVitrine } from 'app/model/produto/produto-vitrine';
import { TipoAtendimentoService } from 'app/service/cadastro/tipo-atendimento.service';
import { Column, ColumnAction, Columns, TypeAction, TypeField, TypeStyle } from 'app/components/shared/table/table/table.component';
import { FormBase } from 'app/view/compartilhado/base/form-base';
import { EnumValues } from 'enum-values';
import * as moment from 'moment';
import { Observable } from 'rxjs';

@Component({
	selector: 'app-cadastro-tipo-atendimento-form',
	templateUrl: './cadastro-tipo-atendimento-form.component.html',
	styleUrls: ['./cadastro-tipo-atendimento-form.component.scss']
})
export class CadastroTipoAtendimentoFormComponent extends FormBase<TipoAtendimento> {

	filteredOptions: Observable<any[]>;
	formControl = new FormControl();
	formAgenda: FormGroup;

	tipos = EnumValues.getNamesAndValues(Tempo);
	semana = EnumValues.getNamesAndValues(EnumDiaSemana);
	produtos: ProdutoVitrine[] = [];
	agenda: TipoAtendimentoAgenda[] = [];
	horarios: Horario[] = []

	dataSource: MatTableDataSource<ProdutoVitrine>;

	dataSourceAgenda: MatTableDataSource<TipoAtendimentoAgenda>;

	displayedColumns: Column[] = [
		new Columns("nome", "Nome", [TypeStyle.WA400]),
		new Columns("preco", "Preço", [TypeStyle.W200], TypeField.CURRENCY),
		new ColumnAction("action", [TypeAction.DELETE])];

	displayedColumns_agenda: Column[] = [
		new Columns("dia", "Dia Semana", [TypeStyle.WA400]),
		new Columns("hora", "Horário", [TypeStyle.WA400]),
		new Columns("vaga", "Vagas", [TypeStyle.W200]),
		new ColumnAction("action", [TypeAction.EDIT, TypeAction.DELETE])];

	constructor(
		protected service: TipoAtendimentoService,
		protected injector: Injector,
		private datePipe: DatePipe
	) {
		super(service, injector);
		this.dataSource = new MatTableDataSource<ProdutoVitrine>(this.produtos);
		this.dataSourceAgenda = new MatTableDataSource<TipoAtendimentoAgenda>(this.agenda);
		this.tabCount = 3;
		this.listarHorarios();
		this.novaAgenda(new TipoAtendimentoAgenda());
	}

	newEntity(): TipoAtendimento {
		return new TipoAtendimento();
	}

	novaAgenda(tipoAtendimentoAgenda: TipoAtendimentoAgenda) {
		this.formAgenda = this.fb.group({
			id: [tipoAtendimentoAgenda.id],
			dia: [tipoAtendimentoAgenda.dia, Validators.required],
			hora: [tipoAtendimentoAgenda.hora, Validators.required],
			vaga: [tipoAtendimentoAgenda.vaga, Validators.required],

		});
	}

	setForm(entity: TipoAtendimento) {
		this.form = this.fb.group({
			id: [{ value: entity.id, disabled: true }],
			nome: [entity.nome, Validators.required],
			tempo: [entity.tempo, Validators.required],
			ativo: [entity.ativo]
		});

		this.produtos = entity.produtos;
		this.dataSource = new MatTableDataSource<ProdutoVitrine>(this.produtos);

		this.agenda = entity.agenda;
		this.dataSourceAgenda = new MatTableDataSource<TipoAtendimentoAgenda>(this.agenda);
	}

	setFormToEntity() {
		this.entity = this.form.getRawValue() as TipoAtendimento;
		this.entity.produtos = this.produtos;
		this.entity.agenda = this.agenda;
	}

	setEntity(response: any) {
		this.entity = response;
		this.setForm(this.entity);
	}


	onEdit(event: Produto) {
		// this.setForm(event);
	}

	onEditAgenda(event: TipoAtendimentoAgenda) {
		this.novaAgenda(event);
		if (event.id == undefined) {
			this.onDeleteAgenda(event);
		}
	}

	onDelete(event: ProdutoVitrine) {
		const index = this.produtos.indexOf(event, 0)
		this.produtos.splice(index, 1);
		this.dataSource = new MatTableDataSource<ProdutoVitrine>(this.produtos);
	}

	onDeleteAgenda(event: TipoAtendimentoAgenda) {
		const index = this.agenda.indexOf(event, 0)
		this.agenda.splice(index, 1);
		this.dataSourceAgenda = new MatTableDataSource<TipoAtendimentoAgenda>(this.agenda);
	}

	onAdicionarProduto(event: ProdutoVitrine) {
		if (event) {
			if (this.produtos.indexOf(event, 0) < 0) {
				this.produtos.push(event);
				this.dataSource = new MatTableDataSource<ProdutoVitrine>(this.produtos);
			}
		}
	}

	onAdicionarAgenda() {
		const tipoAtendimentoAgenda = this.formAgenda.getRawValue() as TipoAtendimentoAgenda;

		let cadastroExiste = false

		this.agenda.forEach(value => {
			if (value.dia == tipoAtendimentoAgenda.dia && value.hora == tipoAtendimentoAgenda.hora) {
				cadastroExiste = true;
				if (value.id > 0 && value.id == tipoAtendimentoAgenda.id) {
					value.hora = tipoAtendimentoAgenda.hora;
					value.vaga = tipoAtendimentoAgenda.vaga;
					value.dia = tipoAtendimentoAgenda.dia;
				}
			} else if (value.id > 0 && value.id == tipoAtendimentoAgenda.id) {

				value.hora = tipoAtendimentoAgenda.hora;
				value.vaga = tipoAtendimentoAgenda.vaga;
				value.dia = tipoAtendimentoAgenda.dia;
				cadastroExiste = true;

			}
		});

		if (!cadastroExiste) {
			this.agenda.push(tipoAtendimentoAgenda);
			this.dataSourceAgenda = new MatTableDataSource<TipoAtendimentoAgenda>(this.agenda);
			this.novaAgenda(new TipoAtendimentoAgenda())
		}

	}

	listarHorarios() {
		
		this.entity = this.form.getRawValue() as TipoAtendimento;
		if (this.entity.tempo > 0) {
			let duracao = this.entity.tempo;
		}

		if (this.entity.tempo  > 0) {
			let data = new Date();
			data.setHours(7, 0)
			this.horarios.push({ value: this.datePipe.transform(data, 'HH:mm') + ':00', name: this.datePipe.transform(data, 'HH:mm') + ':00' });

			while (data.getHours() < 19) {
				data = moment(data).add(this.entity.tempo, 'm').toDate();
				this.horarios.push({ value: this.datePipe.transform(data, 'HH:mm') + ':00', name: this.datePipe.transform(data, 'HH:mm') + ':00' });
			}
		}
	}

	changeFoco(event) {
		if (event == 1) {
			this.onfocus('#inputProduto');
		}
		else if (event == 2) {
			// this.onfocus('#dia');
			this.listarHorarios();
		} else {

		}
	}


}
