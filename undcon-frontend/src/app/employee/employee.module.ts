import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxMaskModule } from 'ngx-mask';

import { EmployeeRoutingModule } from '@app/employee/employee.routing.module';
import { EmployeeComponent } from '@app/employee/employee.component';
import { EmployeeEditComponent } from '@app/employee/employee-edit/employee-edit.component';
import { SharedModule } from '@shared/shared.module';
import { EmployeeGuard } from '@guard/employee.guard';

@NgModule({
  declarations: [EmployeeComponent, EmployeeEditComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    EmployeeRoutingModule,
    NgxMaskModule.forRoot()
  ],
  providers: [
    EmployeeGuard
  ]
})
export class EmployeeModule {}
