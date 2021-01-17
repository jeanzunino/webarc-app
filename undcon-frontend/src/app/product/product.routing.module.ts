import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { ProductComponent } from '@app/product/product.component';
import { ProductResolver } from '@app/product/product.resolver';
import { ProductGuard } from '@guard/product.guard';

const routes: Routes = [
  {
    path: '',
    component: ProductComponent,
    canActivate: [
      AuthGuard,
      ProductGuard
    ],
    resolve: {
      items: ProductResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [ProductResolver],
})
export class ProductRoutingModule {}
