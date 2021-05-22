import { ComponentFactory, ComponentFactoryResolver, ComponentRef, Injectable, Injector } from '@angular/core';
import { BodyInjectorService } from 'app/_helpers/plataform/body-injector.service';
import { BehaviorSubject } from 'rxjs';
import { OverlayConfig } from '../overlay-config';
import { OverlayRef } from '../overlay-ref';
import { OverlayComponent } from '../overlay/overlay.component';

@Injectable({
   providedIn: 'root'
})
export class OverlayService {

   private componentFactory: ComponentFactory<OverlayComponent>;

   private overlaySubject = new BehaviorSubject<ComponentRef<OverlayComponent>>(null);

   constructor(
      componentFactoryResolver: ComponentFactoryResolver,
      private injector: Injector,
      private bodyInjector: BodyInjectorService
   ) {
      this.componentFactory = componentFactoryResolver.resolveComponentFactory(OverlayComponent);
   }

   public open(config: OverlayConfig) {
      const componentRef = this.createComponentRef();
      componentRef.instance.config = config;
      this.bodyInjector.stackBeforeAppRoot(componentRef);

      const overlayRef = new OverlayRef(componentRef);
      componentRef.instance.overlayRef = overlayRef;
      this.overlaySubject.next(componentRef);
      return overlayRef;
   }

   private createComponentRef(): ComponentRef<OverlayComponent> {
      return this.componentFactory.create(this.injector);
   }

   get() {
      return this.overlaySubject;
   }
}
