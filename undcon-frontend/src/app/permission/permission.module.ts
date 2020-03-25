import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';

import { PermissionComponent } from '@app/permission/permission.component';
import { PermissionRoutingModule } from '@app/permission/permission.routing.module';

@NgModule({
  declarations: [
    PermissionComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    PermissionRoutingModule
    //SharedModule
  ]
})
export class PermissionModule { }
