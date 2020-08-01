import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { TenantService } from '@service/tenant/tenant.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Tenant } from '@model/tenant';
import { TenantEditComponent } from '@app/tenant/tenant-edit/tenant-edit.component';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { FormatEnum } from '@core/enum/format-enum';

@Component({
  selector: 'app-tenant',
  templateUrl: './tenant.component.html',
})
export class TenantComponent extends GridViewComponent<Tenant> {

  name = null;
  email = null;
  schemaName = null;
  phone = null;

  tableValues = new Table()
    .set('name', 'tenant.name')
    .set('email', 'tenant.email')
    .set('schemaName', 'tenant.schemaName')
    .set('phone', 'tenant.phone', FormatEnum.PHONE_MASK)
    .get();

  constructor(
    service: TenantService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, TenantEditComponent);
  }

  open() {
    this.onClickItem(null);
  }

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), this.name);
    params.set(getQueryFilter('email', QueryFilterEnum.CONTAINS_IC), this.email);
    params.set(getQueryFilter('schemaName', QueryFilterEnum.CONTAINS_IC), this.schemaName);
    params.set(getQueryFilter('phone', QueryFilterEnum.CONTAINS_IC), this.phone);
    this.onSearchParams(params);
  }

  onClear() {
    this.name = null;
    this.email = null;
    this.schemaName = null;
    this.phone = null;
    this.onClearParams();
  }
}
