import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';
import { NgxSpinnerService } from 'ngx-spinner';

import { Sale } from '@model/sale';
import { SaleService } from '@service/sale/sale.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { SharedInjector } from '@shared/shared.module';
import { FormatEnum } from '@app/core/enum/format-enum';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
})
export class SaleComponent extends GridViewComponent<Sale> {

  spinner = SharedInjector.get(NgxSpinnerService);
  tableValues = new Table()
    .set('id', 'sale.id')
    .set('customer.name', 'sale.customer-name')
    .set('saleDate', 'sale.saleDate', FormatEnum.DATE_TIME_PIPE)
    .set('salesman.name', 'sale.salesman-name')
    .set('status', 'sale.status')
    .set('user.login', 'sale.user-login')
    .get();
  name = null;

  constructor(
    service: SaleService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService,
    private router: Router,
    private rt: ActivatedRoute
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(sale: Sale) {
    this.router.navigate((sale === null ? ['new'] : [sale.id]), { relativeTo: this.rt });
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
