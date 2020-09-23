import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PageNotFoundComponent } from '@app/page-not-found/page-not-found.component';
import { LoginComponent } from '@app/login/login.component';
import { AuthGuard } from '@guard/auth/auth.guard';
import { PageEnum } from '@enum/page-enum';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  { path: PageEnum.LOGIN, component: LoginComponent },
  {
    path: PageEnum.HOME,
    loadChildren: () =>
      import('@app/home/home.module').then((m) => m.HomeModule),
  },
  {
    path: PageEnum.USER,
    loadChildren: () =>
      import('@app/user/user.module').then((m) => m.UserModule),
  },
  {
    path: PageEnum.EMPLOYEE,
    loadChildren: () =>
      import('@app/employee/employee.module').then((m) => m.EmployeeModule),
  },
  {
    path: PageEnum.CUSTOMER,
    loadChildren: () =>
      import('@app/customer/customer.module').then((m) => m.CustomerModule),
  },
  {
    path: PageEnum.PROVIDER,
    loadChildren: () =>
      import('@app/provider/provider.module').then((m) => m.ProviderModule),
  },
  {
    path: PageEnum.PRODUCT,
    loadChildren: () =>
      import('@app/product/product.module').then((m) => m.ProductModule),
  },
  {
    path: PageEnum.PRODUCT_CATEGORY,
    loadChildren: () =>
      import('@app/product-category/product-category.module').then(
        (m) => m.ProductCategoryModule
      ),
  },
  {
    path: PageEnum.PERMISSION,
    loadChildren: () =>
      import('@app/permission/permission.module').then(
        (m) => m.PermissionModule
      ),
  },
  {
    path: PageEnum.SERVICE_TYPE,
    loadChildren: () =>
      import('@app/service-type/service-type.module').then(
        (m) => m.ServiceTypeModule
      ),
  },
  {
    path: PageEnum.BANK_CHECK,
    loadChildren: () =>
      import('@app/bank-check/bank-check.module').then(
        (m) => m.BankCheckModule
      ),
  },
  {
    path: PageEnum.INCOME,
    loadChildren: () =>
    import('@app/income/income.module').then(
      (m) => m.IncomeModule
    ),
  },
  {
    path: PageEnum.EXPENSE,
    loadChildren: () =>
    import('@app/expense/expense.module').then(
      (m) => m.ExpenseModule
    ),
  },
  {
    path: '',
    loadChildren: () =>
      import('@app/sale/sale.module').then((m) => m.SaleModule),
  },
  {
    path: '',
    loadChildren: () =>
      import('@app/purchase/purchase.module').then((m) => m.PurchaseModule),
  },

  //{ path: PageEnum.MENU_TEMPLATE, loadChildren: '@app/menuTemplate/menuTemplate.module#ManuTemplateModule'},
  //{ path: PageEnum.CONFIG, loadChildren: '@app/config/config.module#ConfigModule'},
  {
    path: PageEnum.TENANT,
    loadChildren: () =>
      import('@app/tenant/tenant.module').then((m) => m.TenantModule),
  },
  { path: '**', component: PageNotFoundComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class NavbarRoutingModule {}
