import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { TenantService } from '@service/tenant/tenant.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Tenant } from '@model/tenant';

@Component({
  selector: 'app-tenant',
  templateUrl: './tenant.component.html'
})
export class TenantComponent extends GridViewComponent<Tenant> {

  tableValues = new Table().set('email', 'tenant.email').set('phone', 'tenant.phone').set('schemaName', 'tenant.schemaName').get();

  constructor(service: TenantService,
              activatedRoute: ActivatedRoute,
              modalService: MDBModalService) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    //this.openDialog(item, TenantEditComponent);
  }

  open() {
    this.onClickItem(null);
  }
}
