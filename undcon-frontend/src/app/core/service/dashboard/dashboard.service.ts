import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Customer } from '@model/customer';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashBoardService extends EntityService<Customer> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'dashboards');
  }

  getCountCustomersTotal(): Observable<Number> {
    return this.getAllCustomUrl(`${this.baseUrl}/${this.entityUrl}/countCustomersTotal`) as Observable<Number>;
  }

  getCountProductsTotal(): Observable<Number> {
    return this.getAllCustomUrl(`${this.baseUrl}/${this.entityUrl}/countProductsTotal`) as Observable<Number>;
  }

  getCountProvidersTotal(): Observable<Number> {
    return this.getAllCustomUrl(`${this.baseUrl}/${this.entityUrl}/countProvidersTotal`) as Observable<Number>;
  }

  getSaleTotal(): Observable<Number> {
    return this.getAllCustomUrl(`${this.baseUrl}/${this.entityUrl}/sale/total`) as Observable<Number>;
  }

  getTopProductSaled(): Observable<Number> {
    return this.getAllCustomUrl(`${this.baseUrl}/${this.entityUrl}/sale/topProductSaled`) as Observable<Number>;
  }
}
