import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { TenantService } from '@service/tenant/tenant.service';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-tenant',
  templateUrl: './tenant.component.html'
})
export class TenantComponent implements OnInit {

  items = [];
  tableValues = new Table().set('id', 'tenant.id').set('email', 'tenant.email').set('phone', 'tenant.phone').set('schemaName', 'tenant.schemaName').get();

  constructor(private spinner: NgxSpinnerService,
              public service: TenantService,
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