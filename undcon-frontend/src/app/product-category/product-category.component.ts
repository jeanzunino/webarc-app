import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { ProductCategory } from '@model/product-category';
import { ProductCategoryService } from '@service/product-category/product-category.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Page } from '@model/page';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html'
})
export class ProductCategoryComponent extends GridViewComponent <ProductCategory> {

  tableValues = new Table().set('id', 'product-category.id').set('name', 'product-category.name').get();

  constructor(spinner: NgxSpinnerService,
              service: ProductCategoryService,
              activatedRoute: ActivatedRoute) {
              super(spinner, service, activatedRoute);
             }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}
