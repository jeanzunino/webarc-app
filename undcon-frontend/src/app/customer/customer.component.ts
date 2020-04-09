import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CustomerService } from '@service/customer/customer.service';
import { Customer } from '@model/customer';
import { GenericListComponent } from '@component/generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../shared/component/generic-list/generic-list.component.html',
  styleUrls: ['../shared/component/generic-list/generic-list.component.scss']
})
export class CustomerComponent extends GenericListComponent<Customer>  {

  constructor(service: CustomerService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}