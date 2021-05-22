import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteContaTermoAceiteComponent } from './site-conta-termo-aceite.component';

describe('SiteContaTermoAceiteComponent', () => {
  let component: SiteContaTermoAceiteComponent;
  let fixture: ComponentFixture<SiteContaTermoAceiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SiteContaTermoAceiteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteContaTermoAceiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
