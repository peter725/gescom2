import { TestBed } from '@angular/core/testing';

import { SampleContextService } from './sample-context.service';

describe('SampleContextService', () => {
  let service: SampleContextService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SampleContextService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
