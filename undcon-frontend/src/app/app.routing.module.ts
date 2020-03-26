import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PageNotFoundComponent } from '@app/page-not-found/page-not-found.component';
import { LoginComponent } from '@app/login/login.component';
import { AuthGuard } from '@guard/auth/auth.guard';
import { PageEnum } from '@enum/page-enum';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full', canActivate: [AuthGuard] },
  { path: PageEnum.LOGIN, component: LoginComponent },
  { path: PageEnum.HOME, loadChildren: () => import('@app/home/home.module').then(m => m.HomeModule },
  { path: PageEnum.USER, loadChildren: () => import('@app/user/user.module').then(m => m.UserModule)},
  { path: PageEnum.EMPLOYEE, loadChildren: () => import('@app/employee/employee.module').then(m => m.EmployeeModule)},
  { path: PageEnum.CUSTOMER, loadChildren: '@app/customer/customer.module#CustomerModule'},
  { path: PageEnum.PROVIDER, loadChildren: '@app/provider/provider.module#ProviderModule'},
  { path: PageEnum.PRODUCT, loadChildren: '@app/product/product.module#ProductModule'},
  { path: PageEnum.PRODUCT_CATEGORY, loadChildren: '@app/product-category/product-category.module#ProductCategoryModule'},
  { path: PageEnum.PERMISSION, loadChildren: '@app/permission/permission.module#PermissionModule'},
  { path: PageEnum.SERVICE_TYPE, loadChildren: '@app/service-type/service-type.module#ServiceTypeModule'},
  //{ path: PageEnum.FINANCIAL, loadChildren: '@app/financial/financial.module#FinancialModule'},
  //{ path: PageEnum.SALE, loadChildren: '@app/sale/sale.module#SaleModule'},
  //{ path: PageEnum.PURCHASE, loadChildren: '@app/purchase/purchase.module#PurchaseModule'},

  //{ path: PageEnum.MENU_TEMPLATE, loadChildren: '@app/menuTemplate/menuTemplate.module#ManuTemplateModule'},
  //{ path: PageEnum.CONFIG, loadChildren: '@app/config/config.module#ConfigModule'},
  //{ path: PageEnum.TENANT, loadChildren: '@app/tenant/tenant.module#TenantModule'},

  { path: '**', component: PageNotFoundComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
