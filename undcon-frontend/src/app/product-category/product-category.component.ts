import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ProductCategory } from '@model/product-category';
import { ProductCategoryService } from '@service/product-category/product-category.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html'
})
export class ProductCategoryComponent extends GridViewComponent<ProductCategory> {

  tableValues = new Table().set('id', 'product-category.id').set('name', 'product-category.name').set('parent.name', 'product-category.name').get();

  constructor(service: ProductCategoryService,
              activatedRoute: ActivatedRoute) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}