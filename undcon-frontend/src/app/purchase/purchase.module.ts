import { PurchaseDetailComponent } from './purchase-detail/purchase-detail.component';
import { NgxMaskModule } from 'ngx-mask';
import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { PurchaseRoutingModule } from '@app/purchase/purchase.routing.module';
import { PurchaseComponent } from '@app/purchase/purchase.component';
import { SharedModule } from '@shared/shared.module';
import { PurchaseGuard } from '@guard/purchase.guard';

@NgModule({
  declarations: [PurchaseComponent, PurchaseDetailComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    PurchaseRoutingModule,
    NgxMaskModule.forRoot()
  ],
  providers: [
    PurchaseGuard
  ]
})
export class PurchaseModule {}
