import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { Employee } from '@app/core/model/employee';
import { EmployeeService } from '@app/core/service/employee/employee.service';
import { GenericListResolver } from '@component-generic-list/generic-list.resolver';


@Injectable()
export class EmployeeResolver extends GenericListResolver<Employee> {
  constructor(private service: EmployeeService) {
    super(service)
  }
}
