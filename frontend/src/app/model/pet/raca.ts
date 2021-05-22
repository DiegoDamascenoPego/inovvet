import { TipoAnimal } from './tipo.enum';
export class Raca {
    id: number;
    tipo: TipoAnimal;
    descricao: string;

    constructor(tipo?: TipoAnimal) {
        if (tipo) {
            this.tipo = tipo
        } else {
            this.tipo = TipoAnimal.Cachorro;
        }
        this.descricao = '';
    }
}
