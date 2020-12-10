import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { PurchaseComponent } from '@app/purchase/purchase.component';
import { EmptyComponent } from '@component/empty/empty.component';
import { PageEnum } from '@enum/page-enum';
import { PurchaseResolver } from '@app/purchase/purchase.resolver';
import { EmptyChildrenComponent } from '@component/empty-children/empty-children.component';
import { PurchaseDetailResolver, PurchaseItensResolver, PurchaseExpenseResolver } from './purchase.resolver';
import { PurchaseDetailComponent } from './purchase-detail/purchase-detail.component';

const routes: Routes = [
  {
    path: PageEnum.PURCHASE,
    component: EmptyComponent,
    canActivate: [AuthGuard],
    resolve: {
      items: PurchaseResolver,
    },
    children: [
      {
        path: '',
        component: PurchaseComponent
      },
      {
        path: ':id',
        component: EmptyChildrenComponent,
        resolve: {
          entity: PurchaseDetailResolver,
          purchaseItens: PurchaseItensResolver,
          expenseItens: PurchaseExpenseResolver
        },
        children: [
          {
            path: '',
            component: PurchaseDetailComponent
          }
        ]
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [
    PurchaseResolver,
    PurchaseDetailResolver,
    PurchaseItensResolver,
    PurchaseExpenseResolver
  ]
})
export class PurchaseRoutingModule {}
