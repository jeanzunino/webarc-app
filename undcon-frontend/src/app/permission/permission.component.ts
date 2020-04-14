import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { PermissionService } from '@service/permission/permission.service';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html'
})
export class PermissionComponent implements OnInit {

  items = [];
  tableValues = new Table().set('id', 'permission.id').set('name', 'permission.name').get();

  constructor(private spinner: NgxSpinnerService,
              public service: PermissionService,
              private modalService: MDBModalService) { }

  modalRef: MDBModalRef;            

  async ngOnInit() {
    this.items = await this.service.getAll().toPromise();
    this.spinner.hide();
  }

  async reloadItems(page) {
    this.spinner.show()
    this.items = await this.service.getAll(page).toPromise();
    this.spinner.hide()
  }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}