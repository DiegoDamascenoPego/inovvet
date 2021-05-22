import { Injectable } from '@angular/core';

const ACESS_TOKEN  = 'acess_token';
const USER_TOKEN   = 'utoken';
const CONTA_TOKEN  = 'ctoken';

@Injectable({
   providedIn: 'root'
})
export class TokenService {

   constructor() { }

   armazenarToken(acessToken, uToken, cToken: string) {
      localStorage.setItem(ACESS_TOKEN, acessToken);
      localStorage.setItem(USER_TOKEN, uToken);
      localStorage.setItem(CONTA_TOKEN, cToken);
   }

   carregarToken() {
      return localStorage.getItem(ACESS_TOKEN);
   }

   carregarUserToken() {
      return localStorage.getItem(USER_TOKEN);
   }

   carregarContaToken() {
      return localStorage.getItem(CONTA_TOKEN);
   }

   removerToken(){
      localStorage.removeItem(ACESS_TOKEN);
      localStorage.removeItem(USER_TOKEN);
      localStorage.removeItem(CONTA_TOKEN);
   }
}
