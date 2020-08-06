import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { ProductCategory } from '@model/product-category';
import { ProductCategoryService } from '@service/product-category/product-category.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { ProductCategoryEditComponent } from '@app/product-category/product-category-edit/product-category-edit.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html',
})
export class ProductCategoryComponent extends GridViewComponent<
  ProductCategory
> {
  tableValues = new Table()
    .set('name', 'product-category.name')
    .set('parent.name', 'product-category.parent-name')
    .get();
  name = null;

  constructor(
    service: ProductCategoryService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, ProductCategoryEditComponent);
  }

  open() {
    this.onClickItem(null);
  }

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), this.name);
    this.onSearchParams(params);
  }

  onClear() {
    this.name = null;
    this.onClearParams();
  }
}
