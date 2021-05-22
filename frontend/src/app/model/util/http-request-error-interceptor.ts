import { Injectable } from '@angular/core';
import { HttpHandler, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { tap, finalize } from 'rxjs/operators';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Injectable({
   providedIn: 'root'
})
export class HttpRequestErrorInterceptor {

   constructor(
      private router: Router,
      private spinner: NgxSpinnerService
   ) { }

   intercept(request: HttpRequest<any>, next: HttpHandler): any {

      return next.handle(request).pipe(
         tap(
            event => { },
            error => {
               if (error instanceof HttpErrorResponse) {
                  if (error.status !== 401) {
                     return;
                  }
                  this.router.navigate(['/login']);
               }
            }
         ),
         finalize(() => {
            this.spinner.hide();
         }));

   }
}
