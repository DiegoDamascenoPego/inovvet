import { AbstractModel } from './../abstract-model';
import { EnumTipoArquivo } from './enum-tipo-arquivo.enum';
import { EnumModulo } from './enum-modulo.enum';
import { InputFile } from 'ngx-input-file';
import { EnumReferenciaArquivo } from './enum-referencia-arquivo.enum';
export class Arquivo extends AbstractModel {
   referencia: EnumReferenciaArquivo;
   referenciaId: number;
   nome: string;
   tamanho: number;
   chave: string;
   tipo: EnumTipoArquivo;
   modulo: EnumModulo;
   url: string;

   constructor(id?: number, chave?: string, url?: string) {
      super();
      this.id = id;
      this.chave = chave;
      this.url = url;
   }
}

export class ArquivoView extends Arquivo implements InputFile {

   private _preview: string;

   private _name: string;

   public file: File;

   get preview(): string {
      return this.url;
   }

   get name(): string {
      return this.nome;
   }

}

export interface DefinicaoArquivo {
   tipo: EnumTipoArquivo;
   modulo: EnumModulo;
   referencia: EnumReferenciaArquivo;
   referenciaId: number;
}
