import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Sale } from '@model/sale';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';
import { Page } from '@model/page';
import { SaleItem } from '@model/sale-item';
import { Item } from '@model/item';
import { SaleTotal } from '@model/sale-total';
import { SaleIncome } from '@model/sale-income';

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

  getSaleTotal(saleId: number): Observable<SaleTotal> {
    return this.getCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/total`) as Observable<SaleTotal>;
  }

  launchPaymentSalesIncomes(saleId: number, salesIncomes: SaleIncome[]) {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/toBillList`, salesIncomes);
  }

  launchPaymentSaleIncome(saleId: number, saleIncome: SaleIncome) {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/toBill`, saleIncome);
  }

  saleFinalize(saleId: number) {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/finalize`);
  }

  saleCancel(saleId: number) {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${saleId}/toCancel`);
  }
}
