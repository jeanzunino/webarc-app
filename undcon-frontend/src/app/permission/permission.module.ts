import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { PermissionComponent } from '@app/permission/permission.component';
import { PermissionRoutingModule } from '@app/permission/permission.routing.module';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [
    PermissionComponent
  ],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    PermissionRoutingModule
  ]
})
export class PermissionModule { }