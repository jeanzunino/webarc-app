import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { ExpenseResolver } from './expense.resolver';
import { ExpenseComponent } from './expense.component';
import { ExpenseGuard } from '@guard/expense.guard';

const routes: Routes = [
  {
    path: "",
    component: ExpenseComponent,
    canActivate: [
      AuthGuard,
      ExpenseGuard
    ],
    resolve: {
      items: ExpenseResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [ExpenseResolver],
})
export class ExpenseRoutingModule {}
