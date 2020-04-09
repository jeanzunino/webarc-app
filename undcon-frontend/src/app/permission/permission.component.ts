import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { PermissionService } from '@service/permission/permission.service';
import { Permission } from '@model/permission';
import { GenericListComponent } from '@component/generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../shared/component/generic-list/generic-list.component.html',
  styleUrls: ['../shared/component/generic-list/generic-list.component.scss']
})
export class PermissionComponent extends GenericListComponent<Permission>  {

  constructor(service: PermissionService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}