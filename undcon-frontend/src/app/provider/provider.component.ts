import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { Provider } from '@model/provider';
import { ProviderService } from '@service/provider/provider.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { ProviderEditComponent } from '@app/provider/provider-edit/provider-edit.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { FormatEnum } from '@core/enum/format-enum';

@Component({
  selector: 'app-provider',
  templateUrl: './provider.component.html',
})
export class ProviderComponent extends GridViewComponent<Provider> {
  tableValues = new Table()
    .set('name', 'provider.name')
    .set('phone', 'provider.phone', FormatEnum.PHONE_MASK)
    .get();
  name = null;
  phone = null;

  constructor(
    service: ProviderService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, ProviderEditComponent);
  }

  open() {
    this.openDialog(null, ProviderEditComponent);
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
