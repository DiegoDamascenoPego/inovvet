import { Seguimento } from './../seguimento/seguimento';
import { AbstractModel } from './../abstract-model';
export class Departamento extends AbstractModel {
   nome: string;
   ativo: boolean;
   seguimento: Seguimento;

   constructor(){
      super();
      this.nome = '';
      this.seguimento = null;
      this.ativo = true;
   }
}
