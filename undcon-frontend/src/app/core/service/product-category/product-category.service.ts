import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ProductCategory } from '@model/product-category';
import { EntityService } from '@service/entity/entity.service';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class  ProductCategoryService extends EntityService< ProductCategory> {

  constructor(
    protected http: HttpClient,
    protected storageService: StorageService) {
    super(http, storageService, 'productCategories')
  }

  getProductCategories(): Observable< ProductCategory[]> {
    return this.getAll();
  }
}
