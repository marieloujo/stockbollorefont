import { TestBed } from '@angular/core/testing';

import { DemgestGuard } from './demgest.guard';

describe('DemgestGuard', () => {
  let guard: DemgestGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(DemgestGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
