import { TestBed } from '@angular/core/testing';

import { MatGuard } from './mat.guard';

describe('MatGuard', () => {
  let guard: MatGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(MatGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
