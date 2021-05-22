import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter, map, switchMap } from 'rxjs/operators';
import { PlataformDetectorService } from './_helpers/plataform/plataform-detector.service';



@Component({
   selector: 'app-root',
   templateUrl: './app.component.html',
})
export class AppComponent implements OnInit {
   constructor(
      private router: Router,
      private activatedRoute: ActivatedRoute,
      private titleService: Title,
      private plataform: PlataformDetectorService) { }

   ngOnInit(): void {
      if (this.plataform.isPlataformBrowser()) {
         this.router.events
            .pipe(filter(event => event instanceof NavigationEnd))
            .pipe(map(() => this.activatedRoute))
            .pipe(map(route => {
               while (route.firstChild) route = route.firstChild;
               return route
            }))
            .pipe(switchMap(route => route.data))
            .subscribe(event => {
               this.titleService.setTitle('inovvet sistemas');
               if (event.title) this.titleService.setTitle(event.title);
            })
      }


   }

}
