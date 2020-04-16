import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Permission } from '@model/permission';
import { PermissionService } from '@service/permission/permission.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Page } from '@model/page';

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html'
})
export class PermissionComponent extends GridViewComponent <Permission> {

  tableValues = new Table().set('id', 'permission.id').set('name', 'permission.name').get();

  constructor(spinner: NgxSpinnerService,
              service: PermissionService,
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
