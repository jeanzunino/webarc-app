import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { ProductCategory } from '@model/product-category';
import { Page } from '@model/page';
import { ProductCategoryService } from '@service/product-category/product-category.service';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';

@Component({
  selector: 'app-product-category-edit',
  templateUrl: './product-category-edit.component.html'
})
export class ProductCategoryEditComponent extends DefaultEditViewComponent<
  ProductCategory
> {
  productCategories: ProductCategory[];

  constructor(
    public productCategoryModalRef: MDBModalRef,
    modalOptions: ModalOptions,
    toastr: ToastrService,
    translate: TranslateService,
    service: ProductCategoryService
  ) {
    super(productCategoryModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      parent: new FormControl(null),
    });
  }

  onLoadValuesEdit(productCategory: ProductCategory) {
    this.getFormGroup().patchValue({
      id: productCategory.id,
      name: productCategory.name,
      parent: productCategory.parent ? productCategory.parent.id : null
    });
  }

  async onLoadData() {
    this.productCategories = ((await this.service.getAll().toPromise()) as Page<
      ProductCategory
    >).content;
  }

  afterValidateFormSave() {
    this.parentForm.setValue(
      this.productCategories.find(productCategory => productCategory.id === +this.parentForm.value)
    );
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

  get parentForm() {
    return this.getFormGroup().get('parent');
  }
}
