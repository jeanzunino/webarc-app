import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { ServiceTypeComponent } from "@app/service-type/service-type.component";
import { ServiceTypeResolver } from "@app/service-type/service-type.resolver";

const routes: Routes = [
  {
    path: "",
    component: ServiceTypeComponent,
    canActivate: [AuthGuard],
    resolve: {
      items: ServiceTypeResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [ServiceTypeResolver],
})
export class ServiceTypeRoutingModule {}
