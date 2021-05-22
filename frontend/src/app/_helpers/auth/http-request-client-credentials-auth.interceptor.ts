import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'environments/environment';
import { AuthService } from './auth.service';
import { mergeMap } from 'rxjs/operators';

@Injectable()
export class HttpRequestClientCredentialsAuthInterceptor implements HttpInterceptor {

  constructor(
    private auth: AuthService
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
   
    if (request.url.indexOf(environment.api_accounts) > -1) {
      
      return this.auth.loginAccounts().pipe(mergeMap(response => {
        
        const dupReq = request.clone({
          headers: request.headers
            .set('Authorization', 'Bearer ' + response.access_token)

        });
    
        return next.handle(dupReq);
      })
      );
    } else {
      return next.handle(request);
    }
  }
}
