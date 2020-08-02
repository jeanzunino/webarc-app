import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { Product } from '@model/product';
import { ProductService } from '@service/product/product.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { ProductEditComponent } from './product-edit/product-edit.component';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';

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
  name = null;
  unit = null;

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

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), this.name);
    params.set(getQueryFilter('unit', QueryFilterEnum.CONTAINS_IC), this.unit);
    this.onSearchParams(params);
  }

  onClear() {
    this.name = null;
    this.unit = null;
    this.onClearParams();
  }
}
