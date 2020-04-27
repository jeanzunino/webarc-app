import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Product } from '@model/product';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';

@Component({
  selector: 'app-permission-edit',
  templateUrl: './permission-edit.component.html',
  styleUrls: ['./permission-edit.component.scss']
})
export class ProductEditComponent extends DefaultEditViewComponent<Product> {

  categories: ProductCategory[];

  constructor(public permissionModalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private service: ProductService,
              categoryService: ProductCategoryService,) {
                super(permissionModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup(){
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      productCategory: new FormControl('', Validators.required)
    });
  }

  onLoadValuesEdit(item: Product){
      this.getFormGroup().patchValue({
        id: item.id,
        name: item.name,
        productCategory: item.productCategory
    });
  }

  async onLoadData() {
    this.categories = (await this.categoryService.getAll().toPromise() as Page<ProductCategory>).content;
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

  get productCategoryForm() {
    return this.getFormGroup().get('productCategory');
  }

}
