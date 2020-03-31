import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Purchase } from '@model/purchase';
import { EntityService } from '@service/entity/entity.service';
import { HttpClient } from '@angular/common/http';
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
