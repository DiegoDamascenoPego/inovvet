import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { VitrineService } from '../site/vitrine.service';
import { SocialService } from '../social/social.service';
import { map } from 'rxjs/internal/operators/map';
import { debug } from 'console';

@Injectable({
   providedIn: 'root'
})
export class SiteGuard implements CanActivate {

   constructor(private vitrineService: VitrineService,
      private titleTagService: SocialService) { }

   canActivate(
      next: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (next.routeConfig.path.indexOf('conta') >= 0) {
         return true;
      }
      
      return this.vitrineService.carregar(next.params['seller'], next.params['sku']).pipe(
         map(
            vitrine => {

               this.titleTagService.setTitle(vitrine.produto.nome);
               this.titleTagService.setSocialMediaTags(
                  'http://www.inovvet.com.br' + state.url,
                  vitrine.loja.nomeFantasia,
                  vitrine.produto.nome + ' R$ ' + vitrine.produto.preco,
                  vitrine.produto.url);
               return true
            }
         ));

   }

}
