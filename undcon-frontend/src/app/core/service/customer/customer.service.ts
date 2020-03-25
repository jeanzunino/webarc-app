import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Customer } from '@model/customer';
import { EntityService } from '@service/entity/entity.service';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService extends EntityService<Customer> {

  constructor(
    protected http: HttpClient,
    protected storageService: StorageService) {
    super(http, storageService, 'customers')
  }

  getCustomers(): Observable<Customer[]> {
    return this.getAll();
  }
}
