import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Product } from '@model/product';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends EntityService<Product> {

  constructor(
    protected http: HttpClient,
    protected storageService: StorageService) {
    super(http, storageService, 'products')
  }

}