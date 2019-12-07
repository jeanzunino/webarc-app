import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '@pages/home/home.component';
import { OutraComponent } from '@pages/outra/outra.component';
import { PageNotFoundComponent } from '@pages/page-not-found/page-not-found.component';
import { LoginComponent } from '@pages/login/login.component';
import { AuthGuard } from '@guards/auth/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'outra', component: OutraComponent, canActivate: [AuthGuard]},
  { path: '**', component: PageNotFoundComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }