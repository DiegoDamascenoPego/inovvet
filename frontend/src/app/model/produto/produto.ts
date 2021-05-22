import { AbstractModel } from '../abstract-model';
import { Arquivo } from '../arquivo/arquivo';
import { ClassificacaoOpcoes } from './../classificao/classificao';
import { EntityBase, SimpleEntity } from './../entity-base';
import { Preco } from './preco';


export class Produto extends AbstractModel {
   ean: string;
   nome: string
   descricao: string;
   tipo: SimpleEntity;
   unidade: SimpleEntity;
   classificacao: ClassificacaoOpcoes;
   marca: EntityBase;
   arquivos: Arquivo[];
   precos: Preco [];
   ativo: boolean;

   constructor(){
      super();
      this.ean = '';
      this.nome = '';
      this.descricao = '';
      this.tipo = new SimpleEntity(1);
      this.unidade = new SimpleEntity(59);
      this.ativo = true;
      this.marca = null;
      this.arquivos = [];
      this.precos = [];
   }
}

export class ProdutoFiltro {
   descricao: string;
   tipo: SimpleEntity;
   ativo: boolean;

   constructor(){
      this.tipo = new SimpleEntity(1);
   }
}
