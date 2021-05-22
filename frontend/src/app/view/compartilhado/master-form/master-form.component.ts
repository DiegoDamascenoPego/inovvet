import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OverlayRef } from 'app/components/overlay/overlay-ref';
import { OverlayService } from 'app/components/overlay/service/overlay.service';

@Component({
   selector: 'app-master-form',
   templateUrl: './master-form.component.html',
   styleUrls: ['./master-form.component.scss']
})
export class MasterFormComponent implements OnInit {

   @Input() titulo: string;
   @Output() closeCadastro = new EventEmitter;

   _pathCadastro = '../';
   _pathGerenciador = './gerenciador';
   private _overlayRef: OverlayRef;
   
   showOverlay = false;

   showCadastro = false;
   showPesquisas = false;


   constructor(
      private route: ActivatedRoute,
      private router: Router,
      private overlayService: OverlayService
   ) { 
     
   }

   ngOnInit() {

      this.overlayService.get().subscribe((componentRef) => {
         if(this.titulo == componentRef.instance.config.title){
            this._overlayRef = componentRef.instance.overlayRef;
            this.showOverlay = true;
         }         
      });

      if (this.router.url.indexOf('gerenciador') > 0 || this.route.snapshot.params['id']) {
         this.showPesquisas = true;
         this._pathCadastro = '../'
         this._pathGerenciador = '../gerenciador'

      } else {
         this._pathCadastro = this.router.url;
         this.showCadastro = true;
      }
   }

   onShowCadastro() {
      this.router.navigate(['' + this.route.snapshot.url[1].path])
   }

   onShowGerenciador() {
      this.router.navigate(['cadastro/' + this.route.snapshot.url[1].path + '/gerenciador'])
   }

   onVoltar() {
      this.showOverlay = false;
      this._overlayRef.close();      
   }


}
