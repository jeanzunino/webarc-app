import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { PurchaseComponent } from '@app/purchase/purchase.component';
import { PurchaseResolver } from '@app/purchase/purchase.resolver';

const routes: Routes = [
    { path: '',
      component: PurchaseComponent,
      canActivate: [AuthGuard],
      resolve: {
        items: PurchaseResolver
      }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    providers: [PurchaseResolver]
})
export class PurchaseRoutingModule {}