import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { CustomerService } from '@service/customer/customer.service';
import { Customer } from '@model/customer';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private customerService: CustomerService) { }

  items: Customer[];

  ngOnInit() {
    this.customerService.getCustomers()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(items => {
      this.items = items;
    });
  }

  onClickItem(item) {
  }
}
