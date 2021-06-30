import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeGestionnaireComponent } from './demande-gestionnaire.component';

describe('DemandeGestionnaireComponent', () => {
  let component: DemandeGestionnaireComponent;
  let fixture: ComponentFixture<DemandeGestionnaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemandeGestionnaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandeGestionnaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
