import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { SaleService } from '@service/sale/sale.service';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html'
})
export class SaleComponent implements OnInit {

  items = [];
  tableValues = new Table().set('id', 'sale.id').set('customer.name', 'sale.customer-name').set('saleDate', 'sale.saleDate').get();

  constructor(private spinner: NgxSpinnerService,
              public service: SaleService,
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