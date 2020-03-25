import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { ServiceTypeComponent } from '@app/service-type/service-type.component';

const routes: Routes = [
    { path: '', component: ServiceTypeComponent, canActivate: [AuthGuard] }
    //{ path: 'naoEncontrado', component: CursoNaoEncontradoComponent },
    //{ path: ':id', component: CursoDetalheComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ServiceTypeRoutingModule {}
