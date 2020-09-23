import { PurchaseExpense } from '../../model/purchase-expense';
import { PurchaseTotal } from './../../model/purchase-total';
import { Item } from './../../model/item';
import { PurchaseItem } from './../../model/purchase-item';
import { Page } from './../../model/page';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Purchase } from '@model/purchase';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root',
})
export class PurchaseService extends EntityService<Purchase> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'purchases');
  }

  getPurchaseItens(purchaseId: number, page: number): Observable<Page<PurchaseItem>> {
    return this.getAllCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/itens`, null, page) as Observable<Page<PurchaseItem>>;
  }

  addProductItem(purchaseId: number, item: Item): Observable<any> {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/itensProducts`, item);
  }

  deleteProductItem(purchaseId: number, productItemId: number): Observable<any> {
    return this.deleteCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/itensProducts/${productItemId}`);
  }

  addServiceItem(purchaseId: number, item: Item): Observable<any> {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/itensServices`, item);
  }

  deleteServiceItem(purchaseId: number, servicetItemId: number): Observable<any> {
    return this.deleteCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/itensServices/${servicetItemId}`);
  }

  getPurchaseTotal(purchaseId: number): Observable<PurchaseTotal> {
    return this.getCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/total`) as Observable<PurchaseTotal>;
  }

  launchPaymentPurchasesExpenses(purchaseId: number, purchasesExpenses: PurchaseExpense[]) {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/toBillList`, purchasesExpenses);
  }

  launchPaymentPurchaseExpense(purchaseId: number, purchasesExpense: PurchaseExpense) {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/toBill`, purchasesExpense);
  }

  purchaseFinalize(purchaseId: number) {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/finalize`);
  }

  purchaseCancel(purchaseId: number) {
    return this.postCustomUrl(`${this.baseUrl}/${this.entityUrl}/${purchaseId}/toCancel`);
  }
}
