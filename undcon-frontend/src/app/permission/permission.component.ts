import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Permission } from '@model/permission';
import { PermissionService } from '@service/permission/permission.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html'
})
export class PermissionComponent extends GridViewComponent<Permission> {

  tableValues = new Table().set('id', 'permission.id').set('name', 'permission.name').get();

  constructor(service: PermissionService,
              activatedRoute: ActivatedRoute) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}
