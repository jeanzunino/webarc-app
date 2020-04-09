import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { EmployeeRoutingModule } from '@app/employee/employee.routing.module';
import { EmployeeComponent } from '@app/employee/employee.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [
    EmployeeComponent
  ],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    EmployeeRoutingModule
  ]
})
export class EmployeeModule { }