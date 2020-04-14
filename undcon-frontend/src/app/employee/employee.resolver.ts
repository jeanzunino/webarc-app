import { Injectable } from '@angular/core';

import { Employee } from '@app/core/model/employee';
import { EmployeeService } from '@app/core/service/employee/employee.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';


@Injectable()
export class EmployeeResolver extends GetAllResolver<Employee> {
  constructor(private service: EmployeeService) {
    super(service)
  }
}