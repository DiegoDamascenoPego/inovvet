
import { Endereco } from "../endereco/endereco";

export class Contato {
   nome: string;
   cpf: string;
   email: string;
   telefone: string;
   endereco: Endereco;
   aceiteContrato: boolean;

   constructor() {
      this.aceiteContrato = false;
      this.endereco = new Endereco();
   }
}

