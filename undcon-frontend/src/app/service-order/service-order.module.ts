import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { SharedModule } from '@shared/shared.module';
import { ServiceOrderComponent } from './service-order.component';
import { ServiceOrderEditComponent } from './service-order-edit/service-order-edit.component';
import { ServiceOrderGuard } from '@app/core/guard/service-order.guard';
import { ServiceOrderRoutingModule } from './service-order.routing.module';
import { NgxMaskModule } from 'ngx-mask';

@NgModule({
  declarations: [ServiceOrderComponent, ServiceOrderEditComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    ServiceOrderRoutingModule,
    NgxMaskModule.forRoot()
  ],
  providers: [
    ServiceOrderGuard
  ]
})
export class ServiceOrderModule {}
