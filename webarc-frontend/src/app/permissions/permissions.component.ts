import { Component, OnInit } from '@angular/core';

import { PermissionsService } from '../services/permissions.service';
import { Permission } from '../model/permission';

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.css']
})
export class PermissionsComponent implements OnInit {

  selected: Permission;

  list: Permission[];

  constructor(private service: PermissionsService) { }

  ngOnInit() {
    this.getAll();
  }

  onSelect(item: Permission): void {
    this.selected = item;
  }

  getAll(): void {
    this.service.getAll()
        .subscribe(heroes => this.list = heroes);
  }

}
