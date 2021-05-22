import { Location } from '@angular/common';
import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioLogin } from 'app/model/Sistema/usuario-login';
import { UsuarioService } from 'app/service/seguranca/usuario.service';
import { Observable } from 'rxjs';
import { ROUTES } from '../sidebar/sidebar.component';
import { SistemaService } from '../../service/seguranca/sistema.service';
import { Loja } from 'app/model/Sistema/loja';
import { LojaService } from 'app/service/cadastro/loja.service';

export interface uri {
   title: string;
   url: string;
}


@Component({
   selector: 'app-navbar',
   templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit {
   private listTitles: any[];
   location: Location;
   mobile_menu_visible: any = 0;
   private toggleButton: any;
   private sidebarVisible: boolean;
   private sidebarMini: boolean;

   usuario: UsuarioLogin;
   loja: Loja;

   constructor(
      location: Location,
      private element: ElementRef,
      private router: Router,
      private route: ActivatedRoute,
      private sistemaService: SistemaService,
      private usuarioService: UsuarioService,
      lojaService: LojaService) {
      this.location = location;
      this.sidebarVisible = false;

      usuarioService.getUsuario()
         .subscribe(usuario => this.usuario = usuario);
      
      lojaService.getLoja()
         .subscribe(loja => this.loja = loja);
   }

   ngOnInit() {
      this.listTitles = ROUTES.filter(listTitle => listTitle);
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
      const toggleButton = this.toggleButton;
      const body = document.getElementsByTagName('body')[0];
      setTimeout(function () {
         toggleButton.classList.add('toggled');
      }, 500);

      body.classList.add('nav-open');

      this.sidebarVisible = true;
   };

   sidebarClose() {
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
         // $('html').removeClass('nav-open');
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


         if (body.querySelectorAll('.main-panel')) {
            document.getElementsByClassName('main-panel')[0].appendChild($layer);
         } else if (body.classList.contains('off-canvas-sidebar')) {
            document.getElementsByClassName('wrapper-full-page')[0].appendChild($layer);
         }

         setTimeout(function () {
            $layer.classList.add('visible');
         }, 100);

         $layer.onclick = function () { //asign a function
            body.classList.remove('nav-open');
            this.mobile_menu_visible = 0;
            $layer.classList.remove('visible');
            setTimeout(function () {
               $layer.remove();
               $toggle.classList.remove('toggled');
            }, 400);
         }.bind(this);

         body.classList.add('nav-open');
         this.mobile_menu_visible = 1;

      }
   };

   getTitle(): uri {

      let titlee = this.location.prepareExternalUrl(this.location.path());
      if (titlee.charAt(0) === '#' || titlee === '/dashboard') {
         titlee = '';
      }

      for (let item = 0; item < this.listTitles.length; item++) {
         const url = this.listTitles[item].path;
         if (url != "") {
            if (titlee.search(url) >= 0) {
               return { title: this.listTitles[item].title, url: titlee };
            }
         }
      }
      return { title: titlee, url: titlee };
   }

   minimizeSidebar() {
      const body = document.getElementsByTagName('body')[0];

      if (this.sidebarMini) {
         body.classList.remove('sidebar-mini');
         this.sidebarMini = false;
      } else {
         body.classList.add('sidebar-mini');
         this.sidebarMini = true;
      }

   }

   logout(){
      this.usuarioService.logout();
   }
}
