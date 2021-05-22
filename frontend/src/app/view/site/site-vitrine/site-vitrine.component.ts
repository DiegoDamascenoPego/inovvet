import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProdutoVitrine } from 'app/model/produto/produto-vitrine';
import { VitrineService } from 'app/service/site/vitrine.service';
import { SocialService } from 'app/service/social/social.service';
import { debug } from 'console';

@Component({
   selector: 'app-site-vitrine',
   templateUrl: './site-vitrine.component.html',
   styleUrls: ['./site-vitrine.component.css']
})
export class SiteVitrineComponent implements OnInit {

   produto: ProdutoVitrine = new ProdutoVitrine();


   constructor(
      private titleTagService: SocialService,
      private vitrine: VitrineService,
      protected route: ActivatedRoute) { }


   ngOnInit(): void {

      this.vitrine.carregar(this.route.snapshot.params['seller'], this.route.snapshot.params['sku']).subscribe(vitrine => {
         this.produto = vitrine.produto;
      });
   }

}
