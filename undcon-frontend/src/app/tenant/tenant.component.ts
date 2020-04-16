import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { TenantService } from '@service/tenant/tenant.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Page } from '@model/page';
import { Tenant } from '@model/tenant';

@Component({
  selector: 'app-tenant',
  templateUrl: './tenant.component.html'
})
export class TenantComponent extends GridViewComponent <Tenant> {

  tableValues = new Table().set('id', 'tenant.id').set('email', 'tenant.email').set('phone', 'tenant.phone').set('schemaName', 'tenant.schemaName').get();

  constructor(spinner: NgxSpinnerService,
              service: TenantService,
              activatedRoute: ActivatedRoute) {
                super(spinner, service, activatedRoute);
              }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}
