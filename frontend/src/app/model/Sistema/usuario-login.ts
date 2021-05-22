import { Loja } from './loja';

export class UsuarioLogin {

    nome: string;
    cpf: string;
    email: string;
    perfil: string;
    token: string;
    lojas: Loja[];
    authorities: string[];
}
