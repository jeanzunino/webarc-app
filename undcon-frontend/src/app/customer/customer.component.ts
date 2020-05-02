import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { Customer } from '@model/customer';
import { CustomerService } from '@service/customer/customer.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { CustomerEditComponent } from '@app/customer/customer-edit/customer-edit.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html'
})
export class CustomerComponent extends GridViewComponent<Customer> {

  tableValues = new Table().set('name', 'customer.name').set('phone', 'customer.phone').get();

  constructor(service: CustomerService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, CustomerEditComponent);
  }

  open() {
    this.onClickItem(null);
  }
}
