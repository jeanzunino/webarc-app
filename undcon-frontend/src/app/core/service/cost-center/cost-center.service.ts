import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { CostCenter } from '@model/cost-center';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root',
})
export class CostCenterService extends EntityService<CostCenter> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'costsCenters');
  }
}
