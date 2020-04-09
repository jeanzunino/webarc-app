import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Sale } from '@model/sale';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class SaleService extends EntityService<Sale> {

  constructor(protected http: HttpClient,
              protected storageService: StorageService) {
    super(http, storageService, 'sales')
  }

}