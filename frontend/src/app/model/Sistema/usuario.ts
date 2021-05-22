export class Usuario {

   nome: string;

   cpf: string;

   email: string;

   perfil: string;

   token: string;

   authorities: string[];

   constructor() {
      this.authorities = new Array;
   }
}

export interface UsuarioDTO {
   nome: string;
   cpf: string;
   email: string;
}

export interface UsuarioSimplesDTO {
   nome?: string;
   token?: string;
   email?: string;
}
