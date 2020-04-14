import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { ProductService } from '@service/product/product.service';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html'
})
export class ProductComponent implements OnInit {

  items = [];
  tableValues = new Table().set('id', 'product.id').set('name', 'product.name').set('unit', 'product.unit')
                           .set('purchasePrice', 'product.purchasePrice').set('salePrice', 'product.salePrice')
                           .set('stock', 'product.stock').set('stockMin', 'product.stockMin').get();

  constructor(private spinner: NgxSpinnerService,
              public service: ProductService,
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