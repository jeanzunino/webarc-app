import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { TenantRoutingModule } from '@app/tenant/tenant.routing.module';
import { TenantComponent } from '@app/tenant/tenant.component';

@NgModule({
  declarations: [
    TenantComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    TenantRoutingModule
    //SharedModule
  ]
})
export class TenantModule { }
