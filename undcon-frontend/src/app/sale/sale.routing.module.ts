import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { SaleComponent } from '@app/sale/sale.component';
import { SaleResolver } from '@app/sale/sale.resolver';
import { PageEnum } from '@app/core/enum/page-enum';
import { SaleDetailComponent } from './sale-detail/sale-detail.component';
import { EmptyComponent } from '@component/empty/empty.component';
import { EmptyChildrenComponent } from '@app/shared/component/empty-children/empty-children.component';

const routes: Routes = [
  {
    path: PageEnum.SALE,
    component: EmptyComponent,
    canActivate: [AuthGuard],
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
  providers: [SaleResolver]
})
export class SaleRoutingModule {}
