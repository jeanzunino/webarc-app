import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxMaskModule } from 'ngx-mask';

import { BankCheckRoutingModule } from '@app/bank-check/bank-check.routing.module';
import { BankCheckComponent } from '@app/bank-check/bank-check.component';
import { SharedModule } from '@shared/shared.module';
import { BankCheckGuard } from '@guard/bank-check.guard';

@NgModule({
  declarations: [BankCheckComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    BankCheckRoutingModule,
    NgxMaskModule.forRoot()
  ],
  providers: [
    BankCheckGuard
  ]
})
export class BankCheckModule {}
