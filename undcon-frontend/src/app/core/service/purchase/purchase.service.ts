import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Purchase } from '@model/purchase';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService extends EntityService<Purchase> {

  constructor(protected http: HttpClient,
              protected storageService: StorageService) {
    super(http, storageService, 'purchases')
  }

}