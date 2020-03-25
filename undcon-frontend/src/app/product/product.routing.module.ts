import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { ProductComponent } from '@app/product/product.component';

const employeeRoutes: Routes = [
    { path: '', component: ProductComponent, canActivate: [AuthGuard] }
    //{ path: 'naoEncontrado', component: CursoNaoEncontradoComponent },
    //{ path: ':id', component: CursoDetalheComponent }
];

@NgModule({
    imports: [RouterModule.forChild(employeeRoutes)],
    exports: [RouterModule]
})
export class ProductRoutingModule {}
