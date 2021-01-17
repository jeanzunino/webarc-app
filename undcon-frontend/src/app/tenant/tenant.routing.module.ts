import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { TenantComponent } from "@app/tenant/tenant.component";
import { TenantResolver } from "@app/tenant/tenant.resolver";
import { TenantGuard } from '@guard/tenant.guard';

const routes: Routes = [
  {
    path: "",
    component: TenantComponent,
    canActivate: [
      AuthGuard,
      TenantGuard
    ],
    resolve: {
      items: TenantResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [TenantResolver],
})
export class TenantRoutingModule {}
