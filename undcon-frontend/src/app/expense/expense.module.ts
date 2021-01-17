import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxMaskModule } from 'ngx-mask';

import { ExpenseRoutingModule } from '@app/expense/expense.routing.module';
import { SharedModule } from '@shared/shared.module';
import { ExpenseComponent } from './expense.component';
import { ExpenseGuard } from '@guard/expense.guard';

@NgModule({
  declarations: [ExpenseComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    ExpenseRoutingModule,
    NgxMaskModule.forRoot()
  ],
  providers: [
    ExpenseGuard
  ]
})
export class ExpenseModule {}
