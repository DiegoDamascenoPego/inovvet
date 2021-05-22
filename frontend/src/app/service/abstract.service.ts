import { HttpClient, HttpParams } from '@angular/common/http';
import { Mensagem } from 'app/model/util/const-utils';
import { Filtro } from 'app/model/util/filtro-util';
import { FunctionsUtil } from 'app/model/util/functions-util';
import { environment } from 'environments/environment';
import { Observable, throwError } from 'rxjs';
import { Icrud } from './icrud';


export class Abstract<T> implements Icrud {

   protected urlApi: string = environment.api;

   constructor(
      protected uri: string,
      protected http: HttpClient) { }


   private save(entity: any): Promise<any> {
      return this.http.post<any>(`${environment.api}${this.uri}`, entity).toPromise();
   }


   private update(entity: any): Promise<any> {
      return this.http.put<any>(`${environment.api}${this.uri}/${entity.id}`, entity).toPromise();
   }

   public filtrar(filtro): Observable<Filtro> {
      if (!filtro) {
         filtro.page = 1;
         filtro.size = 7;
      }

      return this.http.post<Filtro>(`${environment.api}${this.uri}/filtrar`, filtro);
   }

   public salvar(entity: any): Promise<any> {
      if (!entity) {
         return new Promise((resolve, reject) => {
            throw new Error(Mensagem.parametroInvalido);
            resolve(true);
         });
      }

      if (!entity.id) {
         return this.save(entity)
      } else {
         return this.update(entity)
      }
   }

   public buscarPorId(id: number): Observable<T> {
      if (FunctionsUtil.isEmptyId(id)) {
         return throwError(Mensagem.parametroInvalido);
      }
      return this.http.get<any>(`${environment.api}${this.uri}/${id}`);
   }

   public remover(id: number): Promise<void> {
      if (FunctionsUtil.isEmptyId(id)) {
         return new Promise((resolve, reject) => {
            throw new Error(Mensagem.parametroInvalido);
         });
      }
      return this.http.delete<void>(`${environment.api}${this.uri}/${id}`).toPromise();
   }

   public apagar(id: number): Promise<any> {
      if (FunctionsUtil.isEmptyId(id)) {
         return throwError(Mensagem.parametroInvalido).toPromise();
      }
      return this.http.delete<void>(`${environment.api}${this.uri}/${id}`).toPromise();
   }

   public removerTodos(ids: number[]): Observable<void> {
      if (!ids || ids.length <= 0) {
         return throwError(Mensagem.parametroInvalido);
      }
      return this.http.post<void>(`${environment.api}${this.uri}/remover`, ids);
   }

   public listarPorNome(nome): Observable<any> {
      if (FunctionsUtil.isEmpty(nome)) {
         nome = '';
      }
      return this.http.get<Array<any>>(`${environment.api}${this.uri}`,
         { params: new HttpParams().set('nome', nome) });
   }

   public listarTodos(): Promise<any> {
      return this.http.get<Array<any>>(`${environment.api}${this.uri}/listar`).toPromise();
   }

}
