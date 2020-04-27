import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { Permission } from '@model/permission';
import { PermissionService } from '@service/permission/permission.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { PermissionEditComponent } from '@app/permission/permission-edit/permission-edit.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html'
})
export class PermissionComponent extends GridViewComponent<Permission> {

  tableValues = new Table().set('name', 'permission.name').get();

  constructor(service: PermissionService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService) {
      super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, PermissionEditComponent);
  }

  open() {
    this.onClickItem(null);
  }
}
