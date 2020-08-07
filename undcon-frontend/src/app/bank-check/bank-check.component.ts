import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { BankCheck } from '@app/core/model/bank-check';
import { BankCheckService  } from '@service/bank-check/bank-check.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { FormatEnum } from '@core/enum/format-enum';

@Component({
  selector: 'app-bank-ckeck',
  templateUrl: 'bank-check.component.html',
})
export class BankCheckComponent extends GridViewComponent<BankCheck> {
  tableValues = new Table()
    .set('number', 'bank-check.number')
    .set('emitter', 'bank-check.emitter')
    .set('personDocument', 'bank-check.personDocument')
    .get();
  number = null;
  emitter = null;
  personDocument = null;

  constructor(
    service: BankCheckService,
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
    params.set(getQueryFilter('number', QueryFilterEnum.EQUALS), this.number);
    params.set(getQueryFilter('emitter', QueryFilterEnum.CONTAINS_IC), this.emitter);
    params.set(getQueryFilter('personDocument', QueryFilterEnum.CONTAINS_IC), this.personDocument);
    this.onSearchParams(params);
  }

  onClear() {
    this.number = null;
    this.emitter = null;
    this.personDocument = null;
    this.onClearParams();
  }
}
