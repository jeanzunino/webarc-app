import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';

import { EmployeeRoutingModule } from '@app/employee/employee.routing.module';
import { EmployeeComponent } from '@app/employee/employee.component';

@NgModule({
  declarations: [
    EmployeeComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    EmployeeRoutingModule
  ]
})
export class EmployeeModule { }
