import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { PurchaseRoutingModule } from '@app/purchase/purchase.routing.module';
import { PurchaseComponent } from '@app/purchase/purchase.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [
    PurchaseComponent
  ],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    PurchaseRoutingModule
  ]
})
export class PurchaseModule { }
