import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { SaleComponent } from '@app/sale/sale.component';
import { SaleResolver } from '@app/sale/sale.resolver';

const routes: Routes = [
    { path: '',
      component: SaleComponent,
      canActivate: [AuthGuard],
      resolve: {
        items: SaleResolver
      }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    providers: [SaleResolver]
})
export class SaleRoutingModule {}
