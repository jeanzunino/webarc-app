import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { FormatEnum } from '@core/enum/format-enum';
import { Income } from '@app/core/model/income';
import { IncomeService } from '@app/core/service/income/income.service';
import { PaymentStatus } from '@app/core/enum/payment-status';
import { BillingStatus } from '@app/core/enum/billing-status';

@Component({
  selector: 'app-income',
  templateUrl: 'income.component.html',
})
export class IncomeComponent extends GridViewComponent<Income> {
  tableValues = new Table()  
    .set('description', 'income.description')
    .set('duaDate', 'income.duaDate')
    .set('paymentDate', 'income.paymentDate')
    .set('paymentType', 'income.paymentType', FormatEnum.PAYMENT_TYPE)
    .set('value', 'income.value', FormatEnum.MONEY)
    .set('paymentStatus', 'income.paymentStatus', FormatEnum.PAYMENT_STATUS)
    .set('customer.name', 'income.customer')
    .get();
  description = null;
  status: PaymentStatus = PaymentStatus.PENDING;

  statusList = Object.values(PaymentStatus);

  constructor(
    service: IncomeService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    //Não possui tela de adionar
  }

  open() {
    //Não possui tela de adionar
  }

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('description', QueryFilterEnum.CONTAINS_IC), this.description);
    params.set(getQueryFilter('paymentStatus', QueryFilterEnum.EQUALS), this.status);
    this.onSearchParams(params);
  }

  onClear() {
    this.description = null;
    this.status = PaymentStatus.PENDING;
    this.onClearParams();
  }
}
