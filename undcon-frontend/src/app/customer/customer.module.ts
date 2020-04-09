import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { CustomerRoutingModule } from '@app/customer/customer.routing.module';
import { CustomerComponent } from '@app/customer/customer.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [
    CustomerComponent
  ],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    CustomerRoutingModule
  ]
})
export class CustomerModule { }