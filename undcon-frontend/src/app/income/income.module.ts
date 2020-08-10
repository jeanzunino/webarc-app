import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxMaskModule } from 'ngx-mask';

import { IncomeRoutingModule } from '@app/income/income.routing.module';
import { IncomeComponent } from '@app/income/income.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [IncomeComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    IncomeRoutingModule,
    NgxMaskModule.forRoot()
  ],
})
export class IncomeModule {}
