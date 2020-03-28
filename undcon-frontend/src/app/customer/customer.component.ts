import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { CustomerService } from '@service/customer/customer.service';
import { Customer } from '@model/customer';
import { GenericListComponent } from '@app/core/component/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: './customer.component.html',
  styleUrls: ['../core/component/generic-list.component.scss']
})
export class CustomerComponent extends GenericListComponent<Customer>  {

  constructor(private service: CustomerService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
