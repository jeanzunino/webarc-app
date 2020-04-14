import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { ProductCategoryService } from '@service/product-category/product-category.service';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html'
})
export class ProductCategoryComponent implements OnInit {

  items = [];
  tableValues = new Table().set('id', 'product-category.id').set('name', 'product-category.name').set('parent.name', 'product-category.parent-name').get();

  constructor(private spinner: NgxSpinnerService,
              public service: ProductCategoryService,
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