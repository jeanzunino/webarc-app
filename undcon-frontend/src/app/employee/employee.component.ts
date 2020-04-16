import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { MDBModalService, MDBModalRef } from 'angular-bootstrap-md';

import { Employee } from '@app/core/model/employee';
import { EmployeeService } from '@service/employee/employee.service';
import { GridViewComponent } from '@shared/component/grid-view/grid-view.component';
import { Table } from '@shared/model/table';
import { Page } from '@model/page';

@Component({
  selector: 'app-employee',
  templateUrl: 'employee.component.html'
})
export class EmployeeComponent extends GridViewComponent <Employee> {

  tableValues = new Table().set('id', 'employee.id').set('name', 'employee.name').set('phone', 'employee.phone').get();

  constructor(spinner: NgxSpinnerService,
              service: EmployeeService,
              activatedRoute: ActivatedRoute) {
                super(spinner, service, activatedRoute);
            }

  onClickItem(item) {
    this.showDialog(item);
  }

  private showDialog(item = null) {
    alert(item);
  }
}
