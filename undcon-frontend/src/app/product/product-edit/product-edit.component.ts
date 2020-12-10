import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Product } from '@model/product';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';
import { ProductCategoryService } from '@service/product-category/product-category.service';
import { ProductService } from '@service/product/product.service';
import { ProductCategory } from '@app/core/model/product-category';
import { Page } from '@app/core/model/page';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html'
})
export class ProductEditComponent extends DefaultEditViewComponent<Product> {
  productCategories: ProductCategory[];

  constructor(
    public permissionModalRef: MDBModalRef,
    public modalOptions: ModalOptions,
    toastr: ToastrService,
    translate: TranslateService,
    service: ProductService,
    public productCategoryService: ProductCategoryService
  ) {
    super(permissionModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      unit: new FormControl('', Validators.required),
      purchasePrice: new FormControl('', Validators.required),
      salePrice: new FormControl('', Validators.required),
      stock: new FormControl('', Validators.required),
      stockMin: new FormControl('', Validators.required),
      productCategory: new FormControl(null, Validators.required),
      gtin: new FormControl(null)
    });
  }

  onLoadValuesEdit(item: Product) {
    this.getFormGroup().patchValue({
      id: item.id,
      name: item.name,
      unit: item.unit,
      purchasePrice: item.purchasePrice,
      salePrice: item.salePrice,
      stock: item.stock,
      stockMin: item.stock,
      productCategory: item.productCategory.id,
      gtin: item.gtin
    });
  }

  async onLoadData() {
    this.productCategories = ((await this.productCategoryService.getAll().toPromise()) as Page<
      ProductCategory
    >).content;
  }

  afterValidateFormSave() {
    this.productCategoryForm.setValue(
      this.productCategories.find(productCategory => productCategory.id === +this.productCategoryForm.value)
    );
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

  get unitForm() {
    return this.getFormGroup().get('unit');
  }

  get purchasePriceForm() {
    return this.getFormGroup().get('purchasePrice');
  }

  get salePriceForm() {
    return this.getFormGroup().get('salePrice');
  }

  get stockForm() {
    return this.getFormGroup().get('stock');
  }

  get stockMinForm() {
    return this.getFormGroup().get('stockMin');
  }

  get productCategoryForm() {
    return this.getFormGroup().get('productCategory');
  }

  get gtinForm() {
    return this.getFormGroup().get('gtin');
  }
}
