import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { EmployeeService } from '@service/employee/employee.service';
import { Employee } from '@model/employee';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private employeeService: EmployeeService) { }

  employees: Employee[];

  ngOnInit() {
    this.employeeService.getUsers()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(employee => {
      this.employees = employee;
    });
  }

  teste(employee) {
    console.log(employee)
  }

}
