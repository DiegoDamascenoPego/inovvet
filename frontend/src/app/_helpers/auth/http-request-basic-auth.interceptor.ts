import { Injectable } from '@angular/core';
import {
   HttpRequest,
   HttpHandler,
   HttpEvent,
   HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
   providedIn: 'root'
})
export class HttpRequestBasicAuthInterceptor implements HttpInterceptor {

   constructor() { }

   intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

      if (request.url.indexOf('/public/') > -1) {
         request = request.clone({
            setHeaders: {
               Authorization: 'Basic c2l0ZToxMTA1MTczMQ=='
            },
            withCredentials: true
         });
      }
      return next.handle(request);
   }
}
