import { Cidade } from './cidade';
import { EnumTipoEndereco } from './enum-tipo-enedereco.enum';

export class Endereco {
   id: number;
   tipo: EnumTipoEndereco;
   cep: string;
   rua: string;
   bairro: string;
   numero: string;
   complemento: string;
   cidade: Cidade;

   constructor() {
      this.tipo = EnumTipoEndereco.Residencial;
      this.bairro = '';
      this.cep = '';
      this.rua = '';
      this.numero = '',
      this.complemento = ''
      this.cidade = new Cidade();
   }



}



