import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Sale } from '@model/sale';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';
import { Observable } from 'rxjs';
import { Page } from '@model/page';
import { SaleItem } from '@model/sale-item';
import { Item } from '@model/item';

@Injectable({
  providedIn: 'root',
})
export class SaleService extends EntityService<Sale> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'sales');
  }

  getSaleItens(saleId: number, page: number): Observable<Page<SaleItem>> {
    return this.getAllCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/itens`, null, page) as Observable<Page<SaleItem>>;
  }

  addProductItem(saleId: number, item: Item): Observable<any> {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/itensProducts`, item);
  }

  deleteProductItem(saleId: number, productItemId: number): Observable<any> {
    return this.deleteCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/itensProducts/${productItemId}`);
  }

  addServiceItem(saleId: number, item: Item): Observable<any> {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/itensServices`, item);
  }

  deleteServiceItem(saleId: number, servicetItemId: number): Observable<any> {
    return this.deleteCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/itensServices/${servicetItemId}`);
  }
}
