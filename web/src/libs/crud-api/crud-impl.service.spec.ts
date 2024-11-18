import { TestBed } from '@angular/core/testing';

import { CrudImplService } from './crud-impl.service';

describe('CrudImplService', () => {
  let service: CrudImplService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CrudImplService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
