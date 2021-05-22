export class Telefone {
    id: number;
    tipo: Enumtipotelefone;
    numero: string;
    observacao: string;

    

    constructor(tipo: Enumtipotelefone  = Enumtipotelefone.Celular  , numero?: string, observacao?: string) {
        this.tipo = tipo;
        this.numero = numero;
        this.observacao = observacao;
    }

   


}

export enum Enumtipotelefone {
    Celular = ('CELULAR'),
    Residencial = ('RESIDENCIAL'),
    Comercial = ('COMERCIAL'),
}
