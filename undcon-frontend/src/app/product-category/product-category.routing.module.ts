import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { ProductCategoryComponent } from '@app/product-category/product-category.component';
import { ProductCategoryResolver } from '@app/product-category/product-category.resolver';
import { ProductCategoryGuard } from '@guard/product-category.guard';

const routes: Routes = [
  {
    path: '',
    component: ProductCategoryComponent,
    canActivate: [
      AuthGuard,
      ProductCategoryGuard
    ],
    resolve: {
      items: ProductCategoryResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [ProductCategoryResolver],
})
export class ProductCategoryRoutingModule {}
