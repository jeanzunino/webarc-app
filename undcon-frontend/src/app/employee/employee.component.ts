import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';

import { EmployeeService } from '@service/employee/employee.service';
import { Employee } from '@model/employee';
import { GenericListComponent } from '@app/core/component/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: './employee.component.html',
  styleUrls: ['../core/component/generic-list.component.scss']
})
export class EmployeeComponent  extends GenericListComponent<Employee>  {

  constructor(private service: EmployeeService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }
}
