import { Observable } from 'rxjs';
import { Filtro } from 'app/model/util/filtro-util';

export interface Icrud {

    salvar(entity: any): Promise<any>;

    buscarPorId(id: number): Observable<any>;

    listarPorNome(nome: string): Observable<any>;

    remover(id: number):  Promise<any>;

    apagar(id: number): Promise<void>;

    removerTodos(ids: number[]): Observable<void>;

    filtrar(filtro): Observable<Filtro>;

    listarTodos(): Promise<any>


}

