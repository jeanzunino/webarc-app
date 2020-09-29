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
import { Expense } from '@app/core/model/expense';
import { ExpenseService } from '@app/core/service/expense/expense.service';
import { PaymentStatus } from '@app/core/enum/payment-status';

@Component({
  selector: 'app-expense',
  templateUrl: 'expense.component.html',
})
export class ExpenseComponent extends GridViewComponent<Expense> {
  tableValues = new Table()
  .set('description', 'expense.description')
  .set('duaDate', 'expense.duaDate')
  .set('paymentDate', 'expense.paymentDate')
  .set('paymentType', 'expense.paymentType', FormatEnum.PAYMENT_TYPE)
  .set('value', 'expense.value', FormatEnum.MONEY)
  .set('paymentStatus', 'expense.paymentStatus', FormatEnum.PAYMENT_STATUS)
  .set('provider.name', 'expense.provider')
  .get();
  description = null;
  status: PaymentStatus = PaymentStatus.PENDING;

  statusList = Object.values(PaymentStatus);
  
  constructor(
    service: ExpenseService,
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

  public onSearch() {
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
