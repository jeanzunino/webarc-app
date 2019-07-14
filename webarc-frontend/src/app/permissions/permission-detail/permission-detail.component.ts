import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { PermissionsService } from '../../services/permissions.service';
import { Permission } from '../../model/permission';

@Component({
  selector: 'app-permission-detail',
  templateUrl: './permission-detail.component.html',
  styleUrls: ['./permission-detail.component.css']
})
export class PermissionDetailComponent implements OnInit {

  @Input() item: Permission;

  constructor(
    private route: ActivatedRoute,
    private service: PermissionsService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.get();
  }

  get(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.service.get(id)
      .subscribe(item => this.item = item);
  }

  goBack(): void {
    this.location.back();
  }

}
