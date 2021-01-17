import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { IncomeComponent } from "@app/income/income.component";
import { IncomeResolver } from "@app/income/income.resolver";
import { IncomeGuard } from '@guard/income.guard';

const routes: Routes = [
  {
    path: "",
    component: IncomeComponent,
    canActivate: [
      AuthGuard,
      IncomeGuard
    ],
    resolve: {
      items: IncomeResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [IncomeResolver],
})
export class IncomeRoutingModule {}
