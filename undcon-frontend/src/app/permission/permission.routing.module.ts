import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { PermissionComponent } from '@app/permission/permission.component';

const permissionRoutes: Routes = [
    { path: '', component: PermissionComponent, canActivate: [AuthGuard] }
    //{ path: 'naoEncontrado', component: CursoNaoEncontradoComponent },
    //{ path: ':id', component: CursoDetalheComponent }
];

@NgModule({
    imports: [RouterModule.forChild(permissionRoutes)],
    exports: [RouterModule]
})
export class PermissionRoutingModule {}
