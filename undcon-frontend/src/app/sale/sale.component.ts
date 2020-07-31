import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { Sale } from '@model/sale';
import { SaleService } from '@service/sale/sale.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
})
export class SaleComponent extends GridViewComponent<Sale> {
  tableValues = new Table()
    .set('id', 'sale.id')
    .set('customer.name', 'sale.customer-name')
    .set('saleDate', 'sale.saleDate')
    .set('salesman.name', 'sale.salesman-name')
    .set('status', 'sale.status')
    .set('user.login', 'sale.user-login')
    .set('billed', 'sale.billed')
    .get();
  name = null;

  constructor(
    service: SaleService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService,
    private router: Router,
    private rt: ActivatedRoute,
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(sale: Sale) {
    this.router.navigate((sale === null ? [0] : [sale.id]), { relativeTo: this.rt });
  }

  open() {
    this.onClickItem(null);
  }

  onSearch() {
    // const params = new Map<string, string>();
    // params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), this.name);
    // params.set(getQueryFilter('unit', QueryFilterEnum.CONTAINS_IC), this.unit);
    // this.onSearchParams(params);
  }

  onClear() {
    // this.name = null;
    // this.unit = null;
    // this.onClearParams();
  }
}
