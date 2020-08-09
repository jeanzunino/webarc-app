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
    this.onSearchParams(params);
  }

  onClear() {
    this.description = null;
    this.onClearParams();
  }
}
