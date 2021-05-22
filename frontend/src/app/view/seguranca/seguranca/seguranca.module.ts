
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS, HttpErrorResponse } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { HttpRequestInterceptor } from './../../../model/util/http-request-interceptor';
import { HttpAuthInterceptor } from 'app/model/util/http-request-auth-interceptor';
import { HttpRequestErrorInterceptor } from 'app/model/util/http-request-error-interceptor';
import { HttpRequestBasicAuthInterceptor } from 'app/_helpers/auth/http-request-basic-auth.interceptor';
import { HttpRequestClientCredentialsAuthInterceptor } from 'app/_helpers/auth/http-request-client-credentials-auth.interceptor';


@NgModule({
   imports: [
      CommonModule,
      HttpModule,
      HttpClientModule,
   ],
   declarations: [],
   providers: [
      {
         provide: HTTP_INTERCEPTORS,
         useClass: HttpAuthInterceptor,
         multi: true,
      },
      {
         provide: HTTP_INTERCEPTORS,
         useClass: HttpRequestInterceptor,
         multi: true,
      },
      {
         provide: HTTP_INTERCEPTORS,
         useClass: HttpRequestErrorInterceptor,         
         multi: true,
      },
      {
         provide: HTTP_INTERCEPTORS,
         useClass: HttpRequestBasicAuthInterceptor,
         multi: true,
      },
      {
         provide: HTTP_INTERCEPTORS,
         useClass: HttpRequestClientCredentialsAuthInterceptor,
         multi: true,
      },
   ],
})
export class SegurancaModule { }
