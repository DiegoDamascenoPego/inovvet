import { APP_ID } from "@angular/core";

export class EntityBase {
    id: number;
    nome: string;
    ativo: boolean;

    constructor() {
        this.id = null;
        this.nome = '';
        this.ativo = true;
    }
}

export class SimpleEntity {
    id: number;
    nome: string;
    descricao: string;
    ativo: boolean;

    constructor(id?) {
        this.id = id === null ? 0 : id;
        this.nome = '';
        this.descricao = '';
        this.ativo = true;
    }
}
