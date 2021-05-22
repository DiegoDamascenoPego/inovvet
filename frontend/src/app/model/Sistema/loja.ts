import { Endereco } from "../endereco/endereco";
import { EnumTipoLoja } from "../loja/enum-tipo-loja.enum";
import { EnumTipoPessoa } from "../loja/enum-tipo-pessoa.enum";

export class Loja {

   nomeFantasia: string;
   token: string;
}

export class LojaCadastro {
   id: number;
   tipoDocumento: EnumTipoPessoa;
   nomeFantasia: string;
   razaoSocial: string;
   documento: string;
   ie: string;
   telefone: string;
   email: string;
   endereco: Endereco;
   tipoLoja: EnumTipoLoja;
   ativo: boolean;

   constructor() {
      this.tipoDocumento = EnumTipoPessoa.CNPJ;
      this.tipoLoja = EnumTipoLoja["Pet Shop"]
      this.endereco = new Endereco();
      this.telefone = '';
      this.ativo = true;
   }
}

export class LojaFiltro {
   id: number;
   nomeFantasia: string;
   ativo: boolean;
}
