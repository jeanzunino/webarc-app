import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { PermissionService } from '@service/permission/permission.service';
import { Permission } from '@model/permission';
import { GenericListComponent } from '@component-generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../core/component/generic-list/generic-list.component.html',
  styleUrls: ['../core/component/generic-list/generic-list.component.scss']
})
export class PermissionComponent extends GenericListComponent<Permission>  {

  constructor(private service: PermissionService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
