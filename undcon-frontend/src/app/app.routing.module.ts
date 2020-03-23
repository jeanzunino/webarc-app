import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PageNotFoundComponent } from '@app/page-not-found/page-not-found.component';
import { LoginComponent } from '@app/login/login.component';
import { AuthGuard } from '@guard/auth/auth.guard';
import { PageEnum } from '@enum/page-enum';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full', canActivate: [AuthGuard] },
  { path: PageEnum.LOGIN, component: LoginComponent },
  { path: PageEnum.HOME, loadChildren: () => import('@app/home/home.module').then(m => m.HomeModule) },
  { path: PageEnum.USER, loadChildren: () => import('@app/user/user.module').then(m => m.UserModule)},
  { path: PageEnum.EMPLOYEE, loadChildren: () => import('@app/employee/employee.module').then(m => m.EmployeeModule)},
  { path: '**', component: PageNotFoundComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }