import { EntityBase } from './../entity-base';
import { AbstractModel } from 'app/model/abstract-model';

export class Classificao extends AbstractModel {
   departamento: EntityBase;
   categoria: EntityBase;
   grupo: EntityBase;
   subgrupo: EntityBase;
   ativo: boolean;

   constructor() {
      super();
      this.ativo = true;
   }

}

export class ClassificacaoLista {
   id: number;
   descricao: string;
   opcoes: ClassificacaoOpcoes[];
}

export class ClassificacaoOpcoes {
   id: number;
   descricao: string;
   descricaoCompleta: string;
}

