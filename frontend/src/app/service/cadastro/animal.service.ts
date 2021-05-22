import { promise } from 'protractor';
import { Animal } from './../../model/pet/animal';
import { Abstract } from './../abstract.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Raca } from 'app/model/pet/raca';
import { environment } from 'environments/environment';
import { Observable, throwError } from 'rxjs';
import { TipoAnimal } from '../../model/pet/tipo.enum';
import { Cor } from 'app/model/pet/animal';
import { FunctionsUtil } from 'app/model/util/functions-util';
import { EntityBase } from 'app/model/entity-base';
import { Mensagem } from 'app/model/util/const-utils';

@Injectable({
   providedIn: 'root'
})
export class AnimalService extends Abstract<Animal> {

   racas: Raca[];
   cores: String[];

   constructor(
      protected http: HttpClient
   ) {
      super('animal', http)
      this.listarRacas(TipoAnimal.Cachorro);
   }

   listarRacas(tipo: TipoAnimal): Promise<Raca[]> {
      return this.http.get<Array<Raca>>(`${environment.api}animal/raca`,
         { params: new HttpParams().set('tipoAnimal', tipo) }).toPromise().then();
   }

   listarCores(): Observable<Cor[]> {
      return this.http.get<Array<Cor>>(`${environment.api}animal/cor`);
   }

   listarAnimais(any): Promise<any> {
      return this.http.post(`${environment.api}animal/filtrar`, any)
         .toPromise()
         .then();
   }

   public buscarPorClienteId(cliente: EntityBase): Observable<EntityBase[]> {
      if (FunctionsUtil.isEmptyId(cliente.id)) {
         return Observable.throw(Mensagem.parametroInvalido);
      }

      return this.http.get<Array<EntityBase>>(`${environment.api}${this.uri}`,
         { params: new HttpParams().set('clienteId', cliente.id.toString()) });
   }
}
