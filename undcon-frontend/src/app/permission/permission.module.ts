import { NgModule } from "@angular/core";
import { MDBBootstrapModule } from "angular-bootstrap-md";

import { PermissionComponent } from "@app/permission/permission.component";
import { PermissionRoutingModule } from "@app/permission/permission.routing.module";
import { PermissionEditComponent } from "@app/permission/permission-edit/permission-edit.component";
import { SharedModule } from "@shared/shared.module";
import { PermissionGuard } from '@guard/permission.guard';

@NgModule({
  declarations: [PermissionComponent, PermissionEditComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    PermissionRoutingModule,
  ],
  providers: [
    PermissionGuard
  ]
})
export class PermissionModule {}
