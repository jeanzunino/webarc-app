import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { SaleComponent } from '@app/sale/sale.component';
import { SaleResolver, SaleDetailResolver, SaleItensResolver } from '@app/sale/sale.resolver';
import { PageEnum } from '@enum/page-enum';
import { SaleDetailComponent } from './sale-detail/sale-detail.component';
import { EmptyComponent } from '@component/empty/empty.component';
import { EmptyChildrenComponent } from '@shared/component/empty-children/empty-children.component';
import { SaleIncomeResolver } from './sale.resolver';
import { SaleGuard } from '@guard/sale.guard';

const routes: Routes = [
  {
    path: PageEnum.SALE,
    component: EmptyComponent,
    canActivate: [
      AuthGuard,
      SaleGuard
    ],
    resolve: {
      items: SaleResolver,
    },
    children: [
      {
        path: '',
        component: SaleComponent
      },
      {
        path: ':id',
        component: EmptyChildrenComponent,
        resolve: {
          entity: SaleDetailResolver,
          saleItens: SaleItensResolver,
          incomeItens: SaleIncomeResolver
        },
        children: [
          {
            path: '',
            component: SaleDetailComponent
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
    SaleResolver,
    SaleDetailResolver,
    SaleItensResolver,
    SaleIncomeResolver
  ]
})
export class SaleRoutingModule {}
