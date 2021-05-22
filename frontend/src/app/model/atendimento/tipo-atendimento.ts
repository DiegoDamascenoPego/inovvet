import { AbstractModel } from 'app/model/abstract-model';
import { EnumFrequencia } from './enum-frequencia.enum';
import { ProdutoVitrine } from '../produto/produto-vitrine';
import { TipoAtendimentoAgenda } from './tipo-atendimento-agenda';


export class TipoAtendimento extends AbstractModel {
    nome: string;
    tempo: number;
    frequencia: EnumFrequencia;
    produtos: ProdutoVitrine [];
    agenda: TipoAtendimentoAgenda [];
    ativo: boolean;

    constructor(){
        super();
        this.ativo = true;
    }
}

export class TipoAtendimentoFiltro {
    descricao: string;
    ativo: boolean;
    produtos = [];

}


