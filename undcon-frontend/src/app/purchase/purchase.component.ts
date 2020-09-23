
import { NgxSpinnerService } from 'ngx-spinner';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { Purchase } from '@model/purchase';
import { FormatEnum } from '@app/core/enum/format-enum';
import { BillingStatus } from '@enum/billing-status';
import { PurchaseService } from '@service/purchase/purchase.service';
import { SharedInjector } from '@shared/shared.module';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
})
export class PurchaseComponent extends GridViewComponent<Purchase> {

  spinner = SharedInjector.get(NgxSpinnerService);
  tableValues = new Table()
    .set('id', 'sale.id')
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
    service: PurchaseService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService,
    private router: Router,
    private rt: ActivatedRoute
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(sale: Purchase) {
    this.router.navigate((sale === null ? ['new'] : [sale.id]), { relativeTo: this.rt });
  }

  open() {
    this.onClickItem(null);
  }

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('provider.name', QueryFilterEnum.CONTAINS_IC), this.name);
    params.set(getQueryFilter('status', QueryFilterEnum.EQUALS), this.status);
    this.onSearchParams(params);
  }

  public onChangeStatus(saleStatus) {
    this.status = saleStatus;
  }

  onClear() {
    this.name = null;
    this.status = null;
    this.onClearParams();
  }
}
