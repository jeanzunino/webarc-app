import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxMaskModule } from 'ngx-mask';

import { ExpenseRoutingModule } from '@app/expense/expense.routing.module';
import { SharedModule } from '@shared/shared.module';
import { ExpenseComponent } from './expense.component';

@NgModule({
  declarations: [ExpenseComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    ExpenseRoutingModule,
    NgxMaskModule.forRoot()
  ],
})
export class ExpenseModule {}
