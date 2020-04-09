import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { TenantRoutingModule } from '@app/tenant/tenant.routing.module';
import { TenantComponent } from '@app/tenant/tenant.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [
    TenantComponent
  ],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    TenantRoutingModule
    //SharedModule
  ]
})
export class TenantModule { }