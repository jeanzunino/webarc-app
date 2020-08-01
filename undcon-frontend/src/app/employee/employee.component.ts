import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MDBModalService } from 'angular-bootstrap-md';

import { Employee } from '@app/core/model/employee';
import { EmployeeService } from '@service/employee/employee.service';
import { GridViewComponent } from '@component/grid-view/grid-view.component';
import { EmployeeEditComponent } from '@app/employee/employee-edit/employee-edit.component';
import { Table } from '@shared/model/table';
import { QueryFilterEnum } from '@core/enum/query-filter';
import { getQueryFilter } from '@shared/utils/utils';
import { FormatEnum } from '@core/enum/format-enum';

@Component({
  selector: 'app-employee',
  templateUrl: 'employee.component.html',
})
export class EmployeeComponent extends GridViewComponent<Employee> {
  tableValues = new Table()
    .set('name', 'employee.name')
    .set('phone', 'employee.phone', FormatEnum.PHONE_MASK)
    .get();
  name = null;
  phone = null;

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

  onSearch() {
    const params = new Map<string, string>();
    params.set(getQueryFilter('name', QueryFilterEnum.CONTAINS_IC), this.name);
    params.set(getQueryFilter('phone', QueryFilterEnum.CONTAINS_IC), this.phone);
    this.onSearchParams(params);
  }

  onClear() {
    this.name = null;
    this.phone = null;
    this.onClearParams();
  }
}
