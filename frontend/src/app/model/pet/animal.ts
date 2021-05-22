import { TipoAnimal } from './tipo.enum';
import { Sexo } from './../util/sexo.enum';
import { Raca } from './raca';
export class Animal {

    id: number;
    clienteId: number;
    nome: string;
    tipo: TipoAnimal;
    sexo: Sexo;
    raca: Raca;
    cores: String[];
    pelo: string;
    dataNascimento: string;
    castrado: Boolean;
    ativo: boolean;
    constructor() {
        this.cores = ['Branco'];
    }

}

export class AnimalAtendimento{
    tipo: string;
    nome: string;
    raca: string;
}

export class Cor {
    nome: string;
}



