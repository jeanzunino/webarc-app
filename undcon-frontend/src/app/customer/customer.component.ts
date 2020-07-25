import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { Customer } from '@model/customer';
import { CustomerService } from '@service/customer/customer.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { CustomerEditComponent } from '@app/customer/customer-edit/customer-edit.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
})
export class CustomerComponent extends GridViewComponent<Customer> {
  tableValues = new Table()
    .set('name', 'customer.name')
    .set('phone', 'customer.phone', '(00) 00000-0000')
    .get();
  name = null;
  phone = null;

  constructor(
    service: CustomerService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, CustomerEditComponent);
  }

  open() {
    this.onClickItem(null);
  }

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), this.name);
    params.set(getQueryFilter('phone', QueryFilterEnum.CONTAINS_IC), this.phone);
    this.onSearchParams(params);
  }

  onClear() {
    this.name = null;
    this.phone = null;
    this.onClearParams();
  }
}
