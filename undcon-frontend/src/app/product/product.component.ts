import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { Product } from '@model/product';
import { ProductService } from '@service/product/product.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { ProductEditComponent } from './product-edit/product-edit.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
})
export class ProductComponent extends GridViewComponent<Product> {
  tableValues = new Table()
    .set('name', 'product.name')
    .set('unit', 'product.unit')
    .set('purchasePrice', 'product.purchasePrice')
    .set('salePrice', 'product.salePrice')
    .set('stock', 'product.stock')
    .set('stockMin', 'product.stockMin')
    .set('productCategory.name', 'product.stockMin')
    .get();

  constructor(
    service: ProductService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, ProductEditComponent);
  }

  open() {
    this.onClickItem(null);
  }
}
