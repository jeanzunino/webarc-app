import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { IntervalType } from '@enum/interval-type';
import { Customer } from '@model/customer';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';
import { Observable } from 'rxjs';
import { ValueByInterval } from '../dtos/value-by-interval';
import { QueryFilterEnum } from '@app/core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';

@Injectable({
  providedIn: 'root',
})
export class DashBoardService extends EntityService<ValueByInterval> {
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

  getTopProductSaled(startDate:string, endDate:string, size:number){
    let params: {};
    params = {startDate: startDate, endDate: endDate, size: size };
    return this.http.get(`${this.baseUrl}/${this.entityUrl}/sale/topProductSaled`, { params });
  }

  getTotalSaledProductByInterval(startDate:string, endDate:string, intervalType: IntervalType) {
    let params: {};
    params = {startDate: startDate, endDate: endDate, type: intervalType.toString() };
    return this.http.get(`${this.baseUrl}/${this.entityUrl}/sale/totalSaledProductByInterval`, { params });
  }

  getTotalSaledServiceByInterval(startDate:string, endDate:string, intervalType: IntervalType) {
    let params: {};
    params = {startDate: startDate, endDate: endDate, type: intervalType.toString() };
    return this.http.get(`${this.baseUrl}/${this.entityUrl}/sale/totalSaledServiceByInterval`, { params });
  }

  getTotalPurchasedProductByInterval(startDate:string, endDate:string, intervalType: IntervalType) {
    let params: {};
    params = {startDate: startDate, endDate: endDate, type: intervalType.toString() };
    return this.http.get(`${this.baseUrl}/${this.entityUrl}/purchase/totalPurchasedProductByInterval`, { params });
  }

  getTotalPurchasedServiceByInterval(startDate:string, endDate:string, intervalType: IntervalType) {
    let params: {};
    params = {startDate: startDate, endDate: endDate, type: intervalType.toString() };
    return this.http.get(`${this.baseUrl}/${this.entityUrl}/purchase/totalPurchasedServiceByInterval`, { params });
  }

  getTotalIncomeByInterval(startDate:string, endDate:string, intervalType: IntervalType) {
    let params: {};
    params = {startDate: startDate, endDate: endDate, type: intervalType.toString() };
    return this.http.get(`${this.baseUrl}/${this.entityUrl}/income/totalByInterval`, { params });
  }

  getTotalExpenseByInterval(startDate:string, endDate:string, intervalType: IntervalType) {
    let params: {};
    params = {startDate: startDate, endDate: endDate, type: intervalType.toString() };
    return this.http.get(`${this.baseUrl}/${this.entityUrl}/expense/totalByInterval`, { params });
  }
}
