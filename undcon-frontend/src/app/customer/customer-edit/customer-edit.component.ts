import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Customer } from '@model/customer';
import { CustomerService } from '@service/customer/customer.service';
import { DefaultEditViewComponent } from '@component/default-edit-view/default-edit-view.component';
import { Modal } from '@shared/model/modal';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.scss']
})
export class CustomerEditComponent extends DefaultEditViewComponent<Customer> {

  constructor( public customerModalRef: MDBModalRef,
               modalOptions: ModalOptions,
               toastr: ToastrService,
               translate: TranslateService,
               service: CustomerService) {
                super(customerModalRef, modalOptions, toastr, translate, service);
  }

  createFormGroup(){
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl('', Validators.required),
      phone: new FormControl('')
    });
  }

  onLoadValuesEdit(customer: Customer){
      this.getFormGroup().patchValue({
        id: customer.id,
        name: customer.name,
        phone: customer.phone
    });
  }

  get nameForm() {
    return this.getFormGroup().get('name');
  }

  get phoneForm() {
    return this.getFormGroup().get('phone');
  }

}
