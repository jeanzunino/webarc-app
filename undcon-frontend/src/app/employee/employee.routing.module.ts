import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { EmployeeComponent } from "@app/employee/employee.component";
import { EmployeeResolver } from "@app/employee/employee.resolver";
import { EmployeeGuard } from '@guard/employee.guard';

const routes: Routes = [
  {
    path: "",
    component: EmployeeComponent,
    canActivate: [
      AuthGuard,
      EmployeeGuard
    ],
    resolve: {
      items: EmployeeResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [EmployeeResolver],
})
export class EmployeeRoutingModule {}
