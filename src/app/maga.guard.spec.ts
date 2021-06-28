import { TestBed } from '@angular/core/testing';

import { MagaGuard } from './maga.guard';

describe('MagaGuard', () => {
  let guard: MagaGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(MagaGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
