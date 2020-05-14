import { Component } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { MDBModalService } from "angular-bootstrap-md";

import { ProductCategory } from "@model/product-category";
import { ProductCategoryService } from "@service/product-category/product-category.service";
import { GridViewComponent } from "@component/grid-view/grid-view.component";
import { ProductCategoryEditComponent } from "@app/product-category/product-category-edit/product-category-edit.component";
import { Table } from "@shared/model/table";

@Component({
  selector: "app-product-category",
  templateUrl: "./product-category.component.html",
})
export class ProductCategoryComponent extends GridViewComponent<
  ProductCategory
> {
  tableValues = new Table()
    .set("name", "product-category.name")
    .set("parent.name", "product-category.name")
    .get();

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
}
