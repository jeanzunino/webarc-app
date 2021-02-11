import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { ServiceOrderComponent } from "./service-order.component";
import { ServiceOrderDetailResolver, ServiceOrderResolver } from "./service-order.resolver";
import { ServiceOrderGuard } from "@app/core/guard/service-order.guard";
import { PageEnum } from "@app/core/enum/page-enum";
import { EmptyComponent } from "@app/shared/component/empty/empty.component";
import { EmptyChildrenComponent } from "@app/shared/component/empty-children/empty-children.component";
import { ServiceOrderEditComponent } from "./service-order-edit/service-order-edit.component";

const routes: Routes = [
  {
    path: PageEnum.SERVICE_ORDER,
    component: EmptyComponent,
    canActivate: [
      AuthGuard,
      ServiceOrderGuard
    ],
    resolve: {
      items: ServiceOrderResolver,
    },
    children: [
      {
        path: '',
        component: ServiceOrderComponent
      },
      {
        path: ':id',
        component: EmptyChildrenComponent,
        resolve: {
          entity:  ServiceOrderDetailResolver
        },
        children: [
          {
            path: '',
            component: ServiceOrderEditComponent
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
    ServiceOrderResolver,
    ServiceOrderDetailResolver
  ]
})
export class ServiceOrderRoutingModule {}
