import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { CostCenterComponent } from "@app/cost-center/cost-center.component";
import { CostCenterResolver } from "@app/cost-center/cost-center.resolver";
import { CostCenterGuard } from '@guard/cost-center.guard';

const routes: Routes = [
  {
    path: "",
    component: CostCenterComponent,
    canActivate: [
      AuthGuard,
      CostCenterGuard
    ],
    resolve: {
      items: CostCenterResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [CostCenterResolver],
})
export class CostCenterRoutingModule {}
