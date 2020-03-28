import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { CustomerRoutingModule } from '@app/customer/customer.routing.module';

import { CustomerComponent } from '@app/customer/customer.component';

@NgModule({
  declarations: [
    CustomerComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    CustomerRoutingModule
    //SharedModule
  ]
})
export class CustomerModule { }
