import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { NgxSpinnerService } from 'ngx-spinner';

import { ProductCategory } from '@model/product-category';
import { Page } from '@model/page';
import { ProductCategoryService } from '@service/product-category/product-category.service';
import { Modal } from '@shared/model/modal';

export class Teste {
  productCategory: ProductCategory
}

@Component({
  selector: 'app-product-category-edit',
  templateUrl: './product-category-edit.component.html',
  styleUrls: ['./product-category-edit.component.scss']
})
export class ProductCategoryEditComponent implements OnInit {

  productCategoryFormGroup: FormGroup;

  categories: ProductCategory[];

  data: Modal

  constructor(public productCategoryModalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private service: ProductCategoryService,
              private spinner: NgxSpinnerService) {
  }

  ngOnInit() {
    this.productCategoryFormGroup = new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      parent: new FormControl(null)
    });
    this.onLoadValues();
    this.spinner.hide();
  }

  async onLoadValues() {
    this.data = this.modalOptions.data as Modal;
    if (this.data.content) {
      const productCategory = this.data.content;
      this.productCategoryFormGroup.patchValue({
        id: productCategory.id,
        name: productCategory.name,
        parent: productCategory.parent
      });
    }
    this.categories = (await this.service.getAll().toPromise() as Page<ProductCategory>).content;
  }

  get nameForm() {
    return this.productCategoryFormGroup.get('name');
  }

  get parentForm() {
    return this.productCategoryFormGroup.get('parent');
  }

  private validForm() {
    return true;
  }


  onSave() {
    if (this.validForm()) {
      if (!this.data.content) {
        this.service.post(this.productCategoryFormGroup.value).toPromise()
        .then(teste => {
          console.log(teste)
        });
      } else {
        this.service.put(this.productCategoryFormGroup.value, parseInt(this.productCategoryFormGroup.get('id').value)).toPromise()
        .then(teste => {
          console.log(teste)
        });
      }
    }
  }
}
