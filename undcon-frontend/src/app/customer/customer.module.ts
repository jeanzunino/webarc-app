import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxMaskModule } from 'ngx-mask';

import { CustomerRoutingModule } from '@app/customer/customer.routing.module';
import { CustomerComponent } from '@app/customer/customer.component';
import { CustomerEditComponent } from '@app/customer/customer-edit/customer-edit.component';
import { SharedModule } from '@shared/shared.module';
import { CustomerGuard } from '@guard/customer.guard';

@NgModule({
  declarations: [CustomerComponent, CustomerEditComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    CustomerRoutingModule,
    NgxMaskModule.forRoot()
  ],
  providers: [
    CustomerGuard
  ]
})
export class CustomerModule {}
