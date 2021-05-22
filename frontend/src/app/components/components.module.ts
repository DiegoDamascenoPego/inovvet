import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import { RouterModule } from '@angular/router';
import { PipeModule } from 'app/components/shared/pipe.module';
import { FooterSiteComponent } from './footer-site/footer-site.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarSiteComponent } from './navbar-site/navbar-site.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    PipeModule, 
    MatTabsModule, 
    MatIconModule
  ],
  declarations: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    NavbarSiteComponent,
    FooterSiteComponent
  ],
  exports: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    NavbarSiteComponent,
    FooterSiteComponent
  ]
})
export class ComponentsModule { }
