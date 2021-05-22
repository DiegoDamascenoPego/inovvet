import { Component, EventEmitter, Input, OnInit, Output, ElementRef, ViewChild, Directive } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Produto } from 'app/model/produto/produto';
import { ProdutoService } from 'app/service/cadastro/produto/produto.service';
import { debounceTime, distinctUntilChanged, filter, switchMap, tap } from 'rxjs/operators';


@Component({
	selector: 'app-filtro-produto',
	templateUrl: './filtro-produto.component.html',
})
export class FiltroProdutoComponent implements OnInit {

	@Output() changeAtualizar = new EventEmitter;
	@Output() changeAdicionar = new EventEmitter;
	@Input() produto: Produto;
	@Input() foco: boolean = false;
	@ViewChild('ComponentFocusIn') setaFoco: ElementRef;


	control = new FormControl();
	filteredOptions: Produto[];

	value: Produto;


	constructor(
		private service: ProdutoService,
	) { }

	ngOnInit(): void {

		this.control.valueChanges.pipe(
			debounceTime(500),
			distinctUntilChanged(),
			tap(() => {
				this.filteredOptions = [];
			}),
			filter(x => typeof x === 'string'),
			switchMap(value => this.service.listarPorNome(value)
				.pipe()
			)
		)
			.subscribe(data => {
				if (data == undefined) {
					this.filteredOptions = [];
				} else {
					this.filteredOptions = data;
				}
			});

		if (this.foco) {
			setTimeout(() => {
				try {
					if (this.setaFoco.nativeElement.value == '') {
						this.setaFoco.nativeElement.focus()
					}
				} catch (error) {

				}
			}, 900);
		}
	}

	display(item: any): string {
		if (item == undefined) { return }
		return item.nome;
	}

	onSelecionar(event) {
		this.produto = event;
		this.changeAtualizar.emit(this.produto);
	}

	onAdicionarProduto() {
		if (this.produto) {
			this.changeAdicionar.emit(this.produto);
			this.value = null;
		}
	}


}
