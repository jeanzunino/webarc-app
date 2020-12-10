import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Customer } from '@model/customer';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root',
})
export class CustomerService extends EntityService<Customer> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'customers');
  }
}
