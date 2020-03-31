import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { Customer } from '@app/core/model/customer';
import { CustomerService } from '@app/core/service/customer/customer.service';
import { GenericListResolver } from '@component-generic-list/generic-list.resolver';


@Injectable()
export class CustomerResolver extends GenericListResolver<Customer> {
  constructor(private service: CustomerService) {
    super(service)
  }
}
