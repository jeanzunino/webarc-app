import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { ServiceTypeComponent } from '@app/service-type/service-type.component';
import { ServiceTypeRoutingModule } from '@app/service-type/service-type.routing.module';
import { ServiceTypeEditComponent } from '@app/service-type/service-type-edit/service-type-edit.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [ServiceTypeComponent, ServiceTypeEditComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    ServiceTypeRoutingModule,
  ],
})
export class ServiceTypeModule {}
