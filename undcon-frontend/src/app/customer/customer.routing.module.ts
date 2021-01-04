import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { CustomerComponent } from "@app/customer/customer.component";
import { CustomerResolver } from "@app/customer/customer.resolver";
import { CustomerGuard } from '@guard/customer.guard';

const routes: Routes = [
  {
    path: "",
    component: CustomerComponent,
    canActivate: [
      AuthGuard,
      CustomerGuard
    ],
    resolve: {
      items: CustomerResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [CustomerResolver],
})
export class CustomerRoutingModule {}
