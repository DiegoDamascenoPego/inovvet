import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LojaService } from 'app/service/cadastro/loja.service';
import { SistemaService } from 'app/service/seguranca/sistema.service';
import { TokenService } from 'app/_helpers/token/token.service';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { Loja } from '../Sistema/loja';

@Injectable({
   providedIn: 'root'
})
export class HttpAuthInterceptor implements HttpInterceptor {

   loja: Loja;
   constructor(
      public tokenService: TokenService,
      private sistemaService: SistemaService,
      lojaservice: LojaService
   ) { 
      lojaservice.getLoja().subscribe(loja => this.loja = loja);
   }

   intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      if (req.url.indexOf('site') > -1) {
         return next.handle(req);
      }

      if (req.url.indexOf('/public/') > -1) {
         return next.handle(req);
      }

      if (req.url.indexOf(environment.api_accounts) > -1) {
         return next.handle(req);
      }

      if (req.url.indexOf(environment.oauthApi) > -1) {
         return next.handle(req);
      }

      if (this.loja?.token && this.tokenService.carregarContaToken()) {

         const dupReq = req.clone({

            headers: req.headers
               .set('Authorization', 'Bearer ' + this.tokenService.carregarToken() || '')
               .set('token', this.tokenService.carregarContaToken() || '')
               .set('seller-active', this.loja.token || '')

         });
         return next.handle(dupReq);
      } else {
         if (this.tokenService.carregarToken()) {
            const dupReq = req.clone({
               headers: req.headers
                  .append('Authorization', 'Bearer ' + this.tokenService.carregarToken() || '')
                  .append('token', this.tokenService.carregarContaToken() || '')
                  .append('Accept', '*/*')
            });
            return next.handle(dupReq);
         } else {
            return next.handle(req);
         }
      }
   }

}
