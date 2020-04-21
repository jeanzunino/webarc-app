import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Customer } from '@model/customer';
import { CustomerService } from '@service/customer/customer.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { CustomerEditComponent } from '@app/customer/customer-edit/customer-edit.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html'
})
export class CustomerComponent extends GridViewComponent<Customer> {

  tableValues = new Table().set('id', 'customer.id').set('name', 'customer.name').set('phone', 'customer.phone').get();
  modalRef: MDBModalRef;

  constructor(service: CustomerService,
    activatedRoute: ActivatedRoute,
    private modalService: MDBModalService) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    this.modalRef = this.modalService.show(CustomerEditComponent, {
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
    this.onClickItem();
  }
}
