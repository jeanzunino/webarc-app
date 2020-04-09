import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { EmployeeService } from '@service/employee/employee.service';
import { Employee } from '@model/employee';
import { GenericListComponent } from '@component/generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../shared/component/generic-list/generic-list.component.html',
  styleUrls: ['../shared/component/generic-list/generic-list.component.scss']
})
export class EmployeeComponent  extends GenericListComponent<Employee>  {

  constructor(service: EmployeeService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}