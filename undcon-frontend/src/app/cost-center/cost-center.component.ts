import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { CostCenter } from '@model/cost-center';
import { CostCenterService } from '@service/cost-center/cost-center.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { CostCenterEditComponent } from '@app/cost-center/cost-center-edit/cost-center-edit.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { FormatEnum } from '@core/enum/format-enum';

@Component({
  selector: 'app-cost-center',
  templateUrl: './cost-center.component.html',
})
export class CostCenterComponent extends GridViewComponent<CostCenter> {
  tableValues = new Table()
    .set('name', 'cost-center.name')
    .get();
  name = null;

  constructor(
    service: CostCenterService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, CostCenterEditComponent);
  }

  open() {
    this.onClickItem(null);
  }

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), this.name);
    this.onSearchParams(params);
  }

  onClear() {
    this.name = null;
    this.onClearParams();
  }
}
