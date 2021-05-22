import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SistemaService } from 'app/service/seguranca/sistema.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { Observable } from 'rxjs';
import { AuthService } from 'app/_helpers/auth/auth.service';

@Injectable({
   providedIn: 'root'
})
export class HttpRequestInterceptor implements HttpInterceptor {

   constructor(
      public authService: AuthService,
      private sistemaService: SistemaService,
      private router: Router,
      private spinner: NgxSpinnerService,
   ) { }

   intercept(
      req: HttpRequest<any>,
      next: HttpHandler,
   ): Observable<HttpEvent<any>> {
     
      if (req.method === 'POST' && req.url.indexOf('arquivo')) { } else {
         const dupReq = req.clone({
            headers: req.headers
               .append('Content-Type', 'application/json; charset=utf8;')
         });
         return next.handle(dupReq);
      }

      return next.handle(req);
   }
}

