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

  items: Employee[];

  ngOnInit() {
    this.employeeService.getUsers()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(items => {
      this.items = items;
    });
  }

  onClickItem(item) {
    console.log(item)
  }

}
