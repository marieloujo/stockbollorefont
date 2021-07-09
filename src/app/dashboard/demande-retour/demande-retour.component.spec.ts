import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeRetourComponent } from './demande-retour.component';

describe('DemandeRetourComponent', () => {
  let component: DemandeRetourComponent;
  let fixture: ComponentFixture<DemandeRetourComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemandeRetourComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandeRetourComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
