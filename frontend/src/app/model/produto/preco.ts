import { LojaService } from 'app/service/cadastro/loja.service';
import { Loja } from '../Sistema/loja';

export class Preco {
   id: number;
   loja: Loja;
   custo: number;
   despesaAdicional: number;
   despesaFixa: number;
   despesaVariavel: number;
   margemLucro: number;
   markup: number;
   valorLucro: number;
   preco: number;

   constructor(loja: Loja) {
      this.custo = 0;
      this.despesaAdicional = 0;
      this.despesaFixa = 0;
      this.despesaVariavel = 0;
      this.margemLucro = 0;
      this.preco = 0;
      this.loja = loja;
   }

}

export class PrecoCalcular {
   preco: number;
   custo: number;
   despesaVariavel: number;
   despesaFixa: number;
   margemLucro: number;

   constructor() {
   }
}
