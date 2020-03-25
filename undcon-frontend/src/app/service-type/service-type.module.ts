import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';

import { ServiceTypeComponent } from '@app/service-type/service-type.component';
import { ServiceTypeRoutingModule } from '@app/service-type/service-type.routing.module';

@NgModule({
  declarations: [
    ServiceTypeComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    ServiceTypeRoutingModule
    //SharedModule
  ]
})
export class ServiceTypeModule { }
