import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

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

  tableValues = new Table().set('id', 'permission.id').set('name', 'permission.name').get();
  modalRef: MDBModalRef;

  constructor(service: PermissionService,
    activatedRoute: ActivatedRoute,
    private modalService: MDBModalService) {
      super(service, activatedRoute);
  }

  onClickItem(item) {
    this.modalRef = this.modalService.show(PermissionEditComponent, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: 'modal-dialog-centered',
      containerClass: '',
      animated: true,
      data: {
        permission: item
      }
    });
  }
}
