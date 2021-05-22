import { EnumDiaSemana } from "./enum-dia-semana.enum";

export class TipoAtendimentoAgenda {

    id: number;

    dia: EnumDiaSemana;

    hora: string;

    vaga: number;

    constructor(){
        this.dia = EnumDiaSemana.Segunda;
        this.hora = '07:00:00'
        this.vaga = 1;
    }
}

export interface Horario {
    value: string;
    name: string;
} 
