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
import { BillingStatus } from '@app/core/enum/billing-status';
import { QueryFilterEnum } from '@app/core/enum/query-filter';
import { getQueryFilter } from '@app/shared/utils/utils';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
})
export class SaleComponent extends GridViewComponent<Sale> {

  spinner = SharedInjector.get(NgxSpinnerService);
  tableValues = new Table()
    .set('customer.name', 'sale.customer-name')
    .set('saleDate', 'sale.saleDate', FormatEnum.DATE_PIPE)
    .set('salesman.name', 'sale.salesman-name')
    .set('status', 'sale.status', FormatEnum.SALE_STATUS)
    .set('user.login', 'sale.user-login')
    .get();
  name = null;
  status: BillingStatus;

  statusList = Object.values(BillingStatus);

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
    const params = new Map<string, string>();
    params.set(getQueryFilter('customer.name', QueryFilterEnum.CONTAINS_IC), this.name);
    params.set(getQueryFilter('status', QueryFilterEnum.EQUALS), this.status);
    this.onSearchParams(params);
  }

  public onChangeStatus(saleStatus){
    this.status = saleStatus;
  }

  onClear() {
    this.name = null;
    this.status = null;
    this.onClearParams();
  }
}
