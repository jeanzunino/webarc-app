import { Component, OnInit } from '@angular/core';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';
import { NgxSpinnerService } from 'ngx-spinner';

import { Table } from '@shared/model/table';
import { CustomerService } from '@service/customer/customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html'
})
export class CustomerComponent implements OnInit {

  items = [];
  tableValues = new Table().set('id', 'customer.id').set('name', 'customer.name').set('phone', 'customer.phone').get();

  constructor(private spinner: NgxSpinnerService,
              public service: CustomerService,
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
    alert(item)
  }
}