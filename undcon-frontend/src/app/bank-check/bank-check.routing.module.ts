import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { BankCheckComponent } from "@app/bank-check/bank-check.component";
import { BankCheckResolver } from "@app/bank-check/bank-check.resolver";
import { BankCheckGuard } from '@guard/bank-check.guard';

const routes: Routes = [
  {
    path: "",
    component: BankCheckComponent,
    canActivate: [
      AuthGuard,
      BankCheckGuard
    ],
    resolve: {
      items: BankCheckResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [BankCheckResolver],
})
export class BankCheckRoutingModule {}
