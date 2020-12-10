import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { BankCheck } from '@model/bank-check';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root',
})
export class BankCheckService extends EntityService<BankCheck> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'checks');
  }
}
