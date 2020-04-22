import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { ProductCategory } from '@model/product-category';
import { ProductCategoryService } from '@service/product-category/product-category.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { ProductCategoryEditComponent } from '@app/product-category/product-category-edit/product-category-edit.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html'
})
export class ProductCategoryComponent extends GridViewComponent<ProductCategory> {

  tableValues = new Table().set('id', 'product-category.id').set('name', 'product-category.name').set('parent.name', 'product-category.name').get();
  modalRef: MDBModalRef;

  constructor(service: ProductCategoryService,
              activatedRoute: ActivatedRoute,
              private modalService: MDBModalService) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    this.modalRef = this.modalService.show(ProductCategoryEditComponent, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: 'modal-dialog-centered',
      containerClass: '',
      animated: true,
      data: {
        content: item
      }
    });
  }

  open() {
    this.onClickItem(null);
  }
}
