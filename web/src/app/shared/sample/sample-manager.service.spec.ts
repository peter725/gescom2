import { TestBed } from '@angular/core/testing';

import { SampleManagerService } from './sample-manager.service';

describe('SampleManagerService', () => {
  let service: SampleManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SampleManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
