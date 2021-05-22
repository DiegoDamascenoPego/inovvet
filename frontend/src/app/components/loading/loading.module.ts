import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoadingComponent } from './loading.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpRequestLoadingInterceptor } from './http-request-loading.interceptor';



@NgModule({
   declarations: [LoadingComponent],
   imports: [
      CommonModule
   ],
   exports: [
      LoadingComponent
   ],
   providers: [
      {
         provide: HTTP_INTERCEPTORS,
         useClass: HttpRequestLoadingInterceptor,
         multi: true
      }
   ]
})
export class LoadingModule { }
