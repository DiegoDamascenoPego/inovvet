import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChange, SimpleChanges } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Setor } from 'app/model/setor/setor';
import { Observable, of } from 'rxjs';
import { catchError, debounceTime, map, startWith, switchMap } from 'rxjs/operators';
import { SetorService } from '../../../service/cadastro/setor.service';

@Component({
  selector: 'app-setor',
  templateUrl: './setor.component.html',
})
export class SetorComponent implements OnInit, OnChanges {


  @Input() setor: Setor;
  @Output() changeAtualizar = new EventEmitter;

  public filteredOptions: Observable<any> = null;
  public searchControl = new FormControl();

  value: any;
  departamentos: Setor[];

  constructor(
    private setorService: SetorService,
  ) { }

  ngOnInit() {
    this.filteredOptions = this.searchControl.valueChanges.pipe(
      startWith(''),
      debounceTime(300),
      switchMap(value => {
        if (value !== '' && typeof value === 'string') {
          return this.listarDepartamento(value);
        } else {
          return of(null);
        }
      })
    );
  }

  ngOnChanges(changes: SimpleChanges): void {
    const departamentoChange: SimpleChange = changes.setor;
    if (departamentoChange.currentValue) {
      this.value = departamentoChange.currentValue;
    }
  }

  private _filter(value, response: any): Setor[] {
    if (value) {
      return response.filter((item: any) => {
        if (typeof value === 'object') { value = '' };
        const TempString = item.descricao;
        return TempString.toLowerCase().includes(value.toLowerCase());
      });
    }
  }

  listarDepartamento(value: string): Observable<any> {
    if (typeof value === 'string') {
      return this.setorService.listarPorNome(value.toLowerCase()).pipe(
        map(results => results),
        catchError(_ => {
          return of(null);
        })
      );
    }
  }

  display(item: any): string {
    if (item == undefined) { return }
    return item.nome;
  }

  clear() {
    this.setor = null;
    this.value = '';
    this.changeAtualizar.emit(this.setor);
  }

  onSelecionar(event) {
    this.setor = event;
    this.changeAtualizar.emit(this.setor);
  }

}
