import { TestBed } from '@angular/core/testing';

import { CatalogueBrowserService } from './catalogue-browser.service';

describe('CatalogueBrowserService', () => {
  let service: CatalogueBrowserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CatalogueBrowserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
