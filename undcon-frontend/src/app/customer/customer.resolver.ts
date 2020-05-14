import { Injectable } from "@angular/core";

import { Customer } from "@app/core/model/customer";
import { CustomerService } from "@app/core/service/customer/customer.service";
import { GetAllResolver } from "@shared/resolver/generic.resolver";

@Injectable()
export class CustomerResolver extends GetAllResolver<Customer> {
  constructor(private service: CustomerService) {
    super(service);
  }
}
