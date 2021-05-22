import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LojaContato } from 'app/model/loja/loja-contato';
import { VitrineService } from 'app/service/site/vitrine.service';
import { PlataformDetectorService } from 'app/_helpers/plataform/plataform-detector.service';

declare const $: any;

@Component({
   selector: 'app-navbar-site',
   templateUrl: './navbar-site.component.html',
   styleUrls: ['./navbar-site.component.css']
})
export class NavbarSiteComponent implements OnInit {

   loja: LojaContato;
   phone = 18996393986;

   isShow = true;

   mobile_menu_visible: any = 0;
   private toggleButton: any;
   private sidebarVisible: boolean;

   constructor(
      private vitrine: VitrineService,
      protected route: ActivatedRoute,
      private router: Router,
      private platform: PlataformDetectorService,
      private element: ElementRef,
   ) {
      this.sidebarVisible = false;
   }

   ngOnInit(): void {

      this.isShow = this.router.routerState.snapshot.url != '/site';

      if (this.router.routerState.snapshot.url.indexOf('vitrine') >= 0 && this.router.routerState.snapshot.url.split('/')[3]) {
         this.vitrine.carregarLoja(this.router.routerState.snapshot.url.split('/')[3]).subscribe((response) => {
            this.loja = response;
         });
      }

      const navbar: HTMLElement = this.element.nativeElement;
      this.toggleButton = navbar.getElementsByClassName('navbar-toggler')[0];
      this.router.events.subscribe((event) => {
         this.sidebarClose();
         var $layer: any = document.getElementsByClassName('close-layer')[0];
         if ($layer) {
            $layer.remove();
            this.mobile_menu_visible = 0;
         }
      });
   }

   sidebarOpen() {
      document.getElementById('sidenav-site').style.width = '250px';
      document.getElementById('main').style.marginRight = '250px';


      this.sidebarVisible = true;
   };
   sidebarClose() {
      document.getElementById('sidenav-site').style.width = '0';
      document.getElementById('main').style.marginRight = '0';

      const body = document.getElementsByTagName('body')[0];
      this.toggleButton.classList.remove('toggled');
      this.sidebarVisible = false;
      body.classList.remove('nav-open');
   };
   sidebarToggle() {
      var $toggle = document.getElementsByClassName('navbar-toggler')[0];

      if (this.sidebarVisible === false) {
         this.sidebarOpen();
      } else {
         this.sidebarClose();
      }
      const body = document.getElementsByTagName('body')[0];

      if (this.mobile_menu_visible == 1) {

         body.classList.remove('nav-open');
         if ($layer) {
            $layer.remove();
         }
         setTimeout(function () {
            $toggle.classList.remove('toggled');
         }, 400);

         this.mobile_menu_visible = 0;
      } else {
         setTimeout(function () {
            $toggle.classList.add('toggled');
         }, 430);

         var $layer = document.createElement('div');
         $layer.setAttribute('class', 'close-layer');

         body.classList.add('nav-open');
         this.mobile_menu_visible = 1;

      }
   };

   isMobileMenu() {
      return this.platform
            .isPlataformBrowser && 
            $(window).width() < 991;
   };

}
