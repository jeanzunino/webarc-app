import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { ProviderComponent } from '@app/provider/provider.component';

const productCategoryRoutes: Routes = [
    { path: '', component: ProviderComponent, canActivate: [AuthGuard] }
    //{ path: 'naoEncontrado', component: CursoNaoEncontradoComponent },
    //{ path: ':id', component: CursoDetalheComponent }
];

@NgModule({
    imports: [RouterModule.forChild(productCategoryRoutes)],
    exports: [RouterModule]
})
export class ProviderRoutingModule {}
