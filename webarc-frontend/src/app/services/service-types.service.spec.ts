import { TestBed } from '@angular/core/testing';

import { ServiceTypesService } from './service-types.service';

describe('ServiceTypesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceTypesService = TestBed.get(ServiceTypesService);
    expect(service).toBeTruthy();
  });
});
