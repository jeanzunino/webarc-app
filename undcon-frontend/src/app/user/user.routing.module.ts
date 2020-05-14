import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { UserComponent } from "@app/user/user.component";
import { UserResolver } from "@app/user/user.resolver";

const routes: Routes = [
  {
    path: "",
    component: UserComponent,
    canActivate: [AuthGuard],
    resolve: {
      items: UserResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [UserResolver],
})
export class UserRoutingModule {}
