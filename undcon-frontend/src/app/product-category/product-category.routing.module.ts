import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { ProductCategoryComponent } from '@app/product-category/product-category.component';

const productCategoryRoutes: Routes = [
    { path: '', component: ProductCategoryComponent, canActivate: [AuthGuard] }
    //{ path: 'naoEncontrado', component: CursoNaoEncontradoComponent },
    //{ path: ':id', component: CursoDetalheComponent }
];

@NgModule({
    imports: [RouterModule.forChild(productCategoryRoutes)],
    exports: [RouterModule]
})
export class ProductCategoryRoutingModule {}
