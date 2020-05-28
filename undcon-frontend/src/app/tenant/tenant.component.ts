import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { TenantService } from '@service/tenant/tenant.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Tenant } from '@model/tenant';
import { TenantEditComponent } from '@app/tenant/tenant-edit/tenant-edit.component';

@Component({
  selector: 'app-tenant',
  templateUrl: './tenant.component.html',
})
export class TenantComponent extends GridViewComponent<Tenant> {
  tableValues = new Table()
    .set('name', 'tenant.name')
    .set('email', 'tenant.email')
    .set('schemaName', 'tenant.schemaName')
    .set('phone', 'tenant.phone', '(00) 00000-0000')
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
}
