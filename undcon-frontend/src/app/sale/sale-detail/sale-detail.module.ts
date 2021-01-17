import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { SharedModule } from '@shared/shared.module';
import { SaleDetailComponent } from '@app/sale/sale-detail/sale-detail.component';

@NgModule({
  declarations: [SaleDetailComponent],
  imports: [SharedModule, MDBBootstrapModule.forRoot()]
})
export class SaleDetailModule {}
