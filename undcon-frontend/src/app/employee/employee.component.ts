import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Employee } from '@app/core/model/employee';
import { EmployeeService } from '@service/employee/employee.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { EmployeeEditComponent } from '@app/employee/employee-edit/employee-edit.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-employee',
  templateUrl: 'employee.component.html'
})
export class EmployeeComponent extends GridViewComponent<Employee> {

  tableValues = new Table().set('id', 'employee.id').set('name', 'employee.name').set('phone', 'employee.phone').get();
  modalRef: MDBModalRef;

  constructor(service: EmployeeService,
    activatedRoute: ActivatedRoute,
    private modalService: MDBModalService) {
    super(service, activatedRoute);
  }

  onClickItem(item) {
    this.modalRef = this.modalService.show(EmployeeEditComponent, {
      backdrop: true,
      keyboard: true,
      focus: true,
      show: false,
      ignoreBackdropClick: false,
      class: 'modal-dialog-centered',
      containerClass: '',
      animated: true,
      data: {
        customer: item
      }
    });
  }
}
