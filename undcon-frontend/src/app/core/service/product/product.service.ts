import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Product } from '@model/product';
import { EntityService } from '@service/entity/entity.service';
import { HttpClient } from '@angular/common/http';
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

  getProducts(): Observable<Product[]> {
    return this.getAll();
  }
}
