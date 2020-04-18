import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

import { Customer } from '@model/customer';
import { CustomerService } from '@service/customer/customer.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html'
})
export class CustomerComponent extends GridViewComponent<Customer> {

  tableValues = new Table().set('id', 'customer.id').set('name', 'customer.name').set('phone', 'customer.phone').get();

  constructor(service: CustomerService,
              activatedRoute: ActivatedRoute) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item)
  }
}
