import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { PermissionComponent } from '@app/permission/permission.component';
import { PermissionResolver } from '@app/permission/permission.resolver';

const routes: Routes = [
  {
    path: '', component: PermissionComponent,
    canActivate: [AuthGuard],
    resolve: {
      items: PermissionResolver
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [PermissionResolver]
})
export class PermissionRoutingModule {}
