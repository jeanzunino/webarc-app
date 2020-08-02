import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxMaskModule } from 'ngx-mask';

import { TenantRoutingModule } from '@app/tenant/tenant.routing.module';
import { TenantComponent } from '@app/tenant/tenant.component';
import { SharedModule } from '@shared/shared.module';
import { TenantEditComponent } from '@app/tenant/tenant-edit/tenant-edit.component';

@NgModule({
  declarations: [TenantComponent, TenantEditComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    TenantRoutingModule,
    NgxMaskModule.forRoot()
  ],
})
export class TenantModule {}
