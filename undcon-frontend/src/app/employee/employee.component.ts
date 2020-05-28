import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { Employee } from '@app/core/model/employee';
import { EmployeeService } from '@service/employee/employee.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { EmployeeEditComponent } from '@app/employee/employee-edit/employee-edit.component';
import { Table } from '@shared/model/table';

@Component({
  selector: 'app-employee',
  templateUrl: 'employee.component.html',
})
export class EmployeeComponent extends GridViewComponent<Employee> {
  tableValues = new Table()
    .set('name', 'employee.name')
    .set('phone', 'employee.phone', '(00) 00000-0000')
    .get();

  constructor(
    service: EmployeeService,
    activatedRoute: ActivatedRoute,
    modalService: MDBModalService
  ) {
    super(service, activatedRoute, modalService);
  }

  onClickItem(item) {
    this.openDialog(item, EmployeeEditComponent);
  }

  open() {
    this.onClickItem(null);
  }
}
