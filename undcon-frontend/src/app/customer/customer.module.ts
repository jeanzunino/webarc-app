import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';

import { CustomerComponent } from '@app/customer/customer.component';

@NgModule({
  declarations: [
    CustomerComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot()
    //SharedModule
  ]
})
export class CustomerModule { }
