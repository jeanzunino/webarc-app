import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { SaleRoutingModule } from '@app/sale/sale.routing.module';
import { SaleComponent } from '@app/sale/sale.component';
import { SharedModule } from '@shared/shared.module';
import { SaleDetailComponent } from './sale-detail/sale-detail.component';
import { NgxMaskModule } from 'ngx-mask';

@NgModule({
  declarations: [SaleComponent, SaleDetailComponent],
  imports: [SharedModule, MDBBootstrapModule.forRoot(), SaleRoutingModule,
    NgxMaskModule.forRoot()],
})
export class SaleModule {}
