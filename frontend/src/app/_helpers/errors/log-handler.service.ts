import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { debug } from 'console';
import { environment } from 'environments/environment';
import { LogHandler } from './log-handler';

@Injectable({
   providedIn: 'root'
})
export class LogHandlerService {

   constructor(
      private http: HttpClient
   ) { }

   log(log: LogHandler) {
      return this.http.post(`${environment.api_resource}public/log`, log);
   }
}
