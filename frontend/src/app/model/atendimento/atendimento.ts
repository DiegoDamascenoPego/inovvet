import { AbstractModel } from './../abstract-model';
import { TipoAtendimento } from './tipo-atendimento';
import { EntityBase } from '../entity-base';
import { EnumStatusAtendimento } from './enum-status-atendimento.enum';
import { Vaga } from './vaga';
import { Cidade } from '../endereco/cidade';
import { Endereco } from '../endereco/endereco';

export class Atendimento extends AbstractModel {
    cliente: EntityBase;
    animal: EntityBase;
    telefones: string;
    data: Date;
    vaga: Vaga;
    status: EnumStatusAtendimento;
    tipoAtendimento?: TipoAtendimento;
    observacao: string;
    endereco: Endereco;

    constructor() {
        super();
        this.data = new Date();
        this.status = EnumStatusAtendimento.Pendente;
        this.endereco = new Endereco();
        this.endereco.cidade = { nome: "Assis", codigo: '3504008', uf: 'SP' };
    };
}


export class AtendimentoFiltro {
    cliente: EntityBase;
    dataInicio: string;
    dataFim: string;
    status: EnumStatusAtendimento;
    tipoAtendimento: TipoAtendimento;


}
