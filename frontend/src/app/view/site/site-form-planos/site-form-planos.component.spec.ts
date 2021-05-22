import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteFormPlanosComponent } from './site-form-planos.component';

describe('SiteFormPlanosComponent', () => {
  let component: SiteFormPlanosComponent;
  let fixture: ComponentFixture<SiteFormPlanosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiteFormPlanosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteFormPlanosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
