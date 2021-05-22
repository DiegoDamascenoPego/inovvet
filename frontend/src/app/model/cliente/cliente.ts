import { AbstractModel } from './../abstract-model';
import { Telefone } from '../telefone/telefone';
import { Sexo } from '../util/sexo.enum';
import { Endereco } from './../endereco/endereco';
import { TipoPessoa } from './tipo-pessoa.enum';


export class Cliente extends AbstractModel {
    tipo: TipoPessoa;
    cpf: string;
    rg: string
    nome: string;
    sexo: Sexo;
    dataNascimento: string;
    email: string;
    observacao: string;
    situacao: boolean;
    endereco: Endereco;
    telefones: Telefone[];
    ativo: boolean;

    constructor() {
        super();
        this.id = 0;
        this.cpf ='';
        this.nome = '';
        this.email = '';
        this.tipo = TipoPessoa.Fisica
        this.sexo = Sexo.Masculino
        this.endereco = new Endereco();
        this.telefones = new Array();
        this.ativo = true;
        this.endereco = new Endereco();
    }
}


export class ClienteFiltro {
    cpf: string;
    nome: string;
    ativo: boolean;

    constructor() { }
}

export class ClienteAtendimento extends AbstractModel {
    nome: string;
}

