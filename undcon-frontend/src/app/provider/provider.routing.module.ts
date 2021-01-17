import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { ProviderComponent } from "@app/provider/provider.component";
import { ProviderResolver } from "@app/provider/provider.resolver";
import { ProviderGuard } from '@guard/provider.guard';

const routes: Routes = [
  {
    path: "",
    component: ProviderComponent,
    canActivate: [
      AuthGuard,
      ProviderGuard
    ],
    resolve: {
      items: ProviderResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [ProviderResolver],
})
export class ProviderRoutingModule {}
