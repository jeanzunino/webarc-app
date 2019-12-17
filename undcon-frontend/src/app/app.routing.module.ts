import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PageNotFoundComponent } from '@app/page-not-found/page-not-found.component';
import { LoginComponent } from '@app/login/login.component';
import { AuthGuard } from '@guard/auth/auth.guard';
import { PageEnum } from '@enum/page-enum';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full', canActivate: [AuthGuard] },
  { path: PageEnum.LOGIN, component: LoginComponent },
  { path: PageEnum.HOME, loadChildren: '@app/home/home.module#HomeModule' },
  { path: PageEnum.USER, loadChildren: '@app/user/user.module#UserModule'},
  { path: PageEnum.EMPLOYEE, loadChildren: '@app/employee/employee.module#EmployeeModule'},
  { path: '**', component: PageNotFoundComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }