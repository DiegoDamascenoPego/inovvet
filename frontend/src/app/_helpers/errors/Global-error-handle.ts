import { LocationStrategy, PathLocationStrategy } from "@angular/common";
import { ErrorHandler, Injectable, Injector } from "@angular/core";
import { environment } from "environments/environment";
import * as StackTrace from "stacktrace-js";
import { PlataformDetectorService } from "../plataform/plataform-detector.service";
import { LogHandlerService } from "./log-handler.service";

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

   constructor(private injector: Injector) { }

   handleError(error: any): void {

      let location = this.injector.get(LocationStrategy);

      let service = this.injector.get(LogHandlerService);

      let plataform = this.injector.get(PlataformDetectorService);

      if (plataform.isPlataformBrowser() && environment.production) {
         const erro = error.message
            ? error.message :
            error.toString();

         const url = location instanceof PathLocationStrategy
            ? location.path()
            : '';

         StackTrace.fromError(error)
            .then(stackFrames => {
               const stackTrace = stackFrames.map(sf => sf.toString())
                  .join('\n');
               service.log({ stackTrace, erro, url }).subscribe();
            })
      }
   }

}