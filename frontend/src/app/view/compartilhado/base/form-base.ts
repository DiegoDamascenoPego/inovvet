import { Injector, OnChanges, SimpleChanges, Input, EventEmitter, NgZone, Renderer2, DebugElement, Directive } from '@angular/core'
import { Output, ElementRef, ViewChild, AfterViewInit, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractModel } from 'app/model/abstract-model';
import { Abstract } from 'app/service/abstract.service';
import { MensagemService } from 'app/util/mensagem.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { SistemaService } from 'app/service/seguranca/sistema.service';
import { MatTabGroup } from '@angular/material/tabs';

export enum EnumFormStatus {
   NOVO,
   EDICAO

}

@Directive()
export abstract class FormBase<T extends AbstractModel> implements OnInit, OnChanges {

   @Input() entitySelecionado: T;

   @Output() closeCadastro = new EventEmitter;
   @Input() Componentefoco: string;

   protected spinner: NgxSpinnerService;
   protected route: ActivatedRoute;
   protected router: Router;
   protected fb: FormBuilder;
   protected msg: MensagemService;
   protected sistemaService: SistemaService;
   public entity: T;
   isSave = false;
   tabCount = 1;
   public form: FormGroup;
   protected ngZone: NgZone;
   protected renderer: Renderer2;

   constructor(
      protected service: Abstract<T>,
      protected injector: Injector,
   ) {
      this.route = this.injector.get(ActivatedRoute);
      this.router = this.injector.get(Router);
      this.fb = this.injector.get(FormBuilder);
      this.msg = this.injector.get(MensagemService);
      this.spinner = this.injector.get(NgxSpinnerService);
      this.sistemaService = this.injector.get(SistemaService);
      this.ngZone = this.injector.get(NgZone);
      this.renderer = this.injector.get(Renderer2);

      this.entity = this.newEntity();
      this.setForm(this.entity);
   }

   abstract setForm(entity: T);
   abstract newEntity(): T;
   abstract setFormToEntity();
   abstract setEntity(response);

   ngOnInit(): void {

      if (this.route.snapshot.data.entity as T) {
         this.onCarregar(this.route.snapshot.data.entity as T);

      }
      else if (this.route.snapshot.params['id']) {
         this.onCarregar(this.route.snapshot.params['id'])
      }
      else {
         setTimeout(() => {
            try {
               this.onfocus(this.Componentefoco)
            } catch (error) {

            }
         });
      }
   }

   ngOnChanges(changes: SimpleChanges): void {
      if (changes.entitySelecionado && changes.entitySelecionado.currentValue) {
         this.onCarregar(changes.entitySelecionado.currentValue.id);
      } else {
         this.setForm(this.newEntity());
      }
   }

   onSalvar() {
      this.form.markAllAsTouched();
      if (this.form.invalid) {
         return;
      }

      this.spinner.show();
      this.setFormToEntity();
      this.service.salvar(this.entity).then(
         (response) => {
            this.setEntity(response);
            this.msg.onSucess('Sucesso', 'Registro salvo.');
         }
      ).catch((response) => {
         if (response.error.tipo == 'Alerta') {
            this.msg.onAlert(response.error.titulo, response.error.descricao);
         } else {
            this.msg.onError(response.error.titulo, response.error.descricao);
         }

      });
   }

   onProximo(component: MatTabGroup) {
      component.selectedIndex += 1;
   }

   onCarregar(value: T | string | number) {

      if (typeof value === 'string') {
         if (parseInt(value) > 0) {
            this.spinner.show();
            this.service.buscarPorId(parseInt(value)).subscribe(
               (response) => {
                  this.setEntity(response);
                  this.isSave = response.id > 0;
               },
               (response) => {
                  this.msg.onError(response.error.titulo, response.error.detalhe);
               });
         }
      } else {
         if (value) {
            const entity = value as T;
            if (entity.id > 0) {
               this.setEntity(entity);
               this.isSave = entity.id > 0;
            }

         }
      }
   }

   onVoltar() {
      this.closeCadastro.emit();
   }

   onShowOverlay(role: string): boolean {
      if (role) {
         if (this.sistemaService.hasAutorityRole('CLIENTE_CADASTRAR')) {
            return true
         } else {
            this.msg.onError('Recurso não liberado para usuário', '');
            return false;
         }
      }
   }

   changeTab(event) {
      if (event == this.tabCount - 1) {
         this.isSave = true;
      }
   }

   onfocus(element: String) {
      if (element) {
         this.ngZone.runOutsideAngular(() => {
            setTimeout(() => {
               let component = this.renderer.selectRootElement(element);
               try {
                  if (component && component.value == '') {
                     component.focus();
                  }
               } catch (error) {

               }
            }, 900);
         });
      }

   }
}



