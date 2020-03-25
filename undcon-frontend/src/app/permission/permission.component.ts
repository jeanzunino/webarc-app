import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { PermissionService } from '@service/permission/permission.service';
import { Permission } from '@model/permission';

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.scss']
})
export class PermissionComponent implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private permissionService: PermissionService) { }

  items: Permission[];

  ngOnInit() {
    this.permissionService.getPermissions()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(items => {
      this.items = items;
    });
  }

  onClickItem(item) {
    console.log(item)
  }
}
