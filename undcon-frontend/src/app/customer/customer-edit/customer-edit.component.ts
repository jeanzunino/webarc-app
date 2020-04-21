import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MDBModalRef, ModalOptions } from 'angular-bootstrap-md';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

import { Customer } from '@model/customer';
import { CustomerService } from '@service/customer/customer.service';

export class Teste {
  customer: Customer
}

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.scss']
})
export class CustomerEditComponent implements OnInit {

  customerFormGroup: FormGroup;

  constructor(public customerModalRef: MDBModalRef,
              public modalOptions: ModalOptions,
              private toastr: ToastrService,
              private translate: TranslateService,
              private service: CustomerService) {
  }

  ngOnInit() {
    this.customerFormGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      phone: new FormControl('')
    });
    let dados = this.modalOptions.data as Teste
    if (dados.customer) {
      this.nameForm.setValue(dados.customer.name);
      this.phoneForm.setValue(dados.customer.phone);
    }

  }

  get nameForm() {
    return this.customerFormGroup.get('name');
  }

  get phoneForm() {
    return this.customerFormGroup.get('phone');
  }

  private validForm() {
    return true;
  }


  onSave() {
    if (this.validForm()) {
      alert(this.nameForm.value)
    }
  }
}
