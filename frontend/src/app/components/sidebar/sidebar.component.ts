import { Component, OnInit } from '@angular/core';
import { Loja } from 'app/model/Sistema/loja';
import { UsuarioLogin } from 'app/model/Sistema/usuario-login';
import { LojaService } from 'app/service/cadastro/loja.service';
import { UsuarioService } from 'app/service/seguranca/usuario.service';

declare const $: any;
declare interface RouteInfo {
   path: string;
   title: string;
   icon: string;
   grupo: string;
   menuName: string;
}
export const ROUTES: RouteInfo[] = [
   { path: '', title: 'Administrativo', icon: 'check_box_outline_blank', grupo: 'cadastro', menuName: 'Admin' },
   { path: '/cadastro/loja', title: 'Loja', icon: 'store', grupo: 'Admin', menuName: '' },

   { path: '', title: 'Cliente', icon: 'check_box_outline_blank', grupo: 'cadastro', menuName: 'cliente' },
   { path: '/cadastro/atendimento', title: 'Atendimento', icon: 'list_alt', grupo: 'cliente', menuName: '' },
   { path: '/cadastro/cliente', title: 'Cliente', icon: 'person', grupo: 'cliente', menuName: '' },

   { path: '', title: 'Produto', icon: 'check_box_outline_blank', grupo: 'cadastro', menuName: 'produto' },
   { path: '/cadastro/categoria', title: 'Categoria', icon: 'picture_in_picture_alt', grupo: 'produto', menuName: '' },
   { path: '/cadastro/classificacao', title: 'Classificação', icon: 'check_box_outline_blank', grupo: 'produto', menuName: 'classificacao' },

   { path: '/cadastro/departamento', title: 'Departamento', icon: 'picture_in_picture', grupo: 'produto', menuName: '' },
   { path: '/cadastro/fabricante', title: 'Fabricante', icon: 'domain', grupo: 'produto', menuName: '' },
   { path: '/cadastro/grupo', title: 'Grupo', icon: 'picture_in_picture', grupo: 'produto', menuName: '' },
   { path: '/cadastro/produto', title: 'Produto', icon: 'dialpad', grupo: 'produto', menuName: '' },
   { path: '/cadastro/subgrupo', title: 'Subgrupo', icon: 'picture_in_picture_alt', grupo: 'produto', menuName: '' },



   { path: '/painel/atendimento/gerenciador', title: 'Atendimento', icon: 'local_library', grupo: 'gerencial', menuName: '' },
   // { path: '/painel/agenda', title: 'Agenda', icon: 'local_library', grupo: 'gerencial', menuName: '' },
];


@Component({
   selector: 'app-sidebar',
   templateUrl: './sidebar.component.html',
   styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
   menuItems: any[];

   loja: Loja
   usuario: UsuarioLogin;

   constructor(
      private usuarioService: UsuarioService,
      lojaService: LojaService
   ) {
      usuarioService.getUsuario().subscribe(usuario => this.usuario = usuario);
      lojaService.getLoja().subscribe(loja => this.loja = loja)
   }

   ngOnInit() {
      this.menuItems = ROUTES.filter(menuItem => menuItem);
   }


   GetMenu(value): any[] {
      return ROUTES.filter(menuItem => {
         if (menuItem.grupo === value) {
            return menuItem;
         }
      });
   }

   isMobileMenu() {
      if ($(window).width() > 991) {
         return false;
      }
      return true;
   };

   logout() {
      this.usuarioService.logout();
   }
}
