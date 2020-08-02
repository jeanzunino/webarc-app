import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "@guard/auth/auth.guard";
import { LoginComponent } from "@app/login/login.component";

const loginRoutes: Routes = [
  { path: "", component: LoginComponent, canActivate: [AuthGuard] },
  //{ path: 'naoEncontrado', component: CursoNaoEncontradoComponent },
  //{ path: ':id', component: CursoDetalheComponent }
];

@NgModule({
  imports: [RouterModule.forChild(loginRoutes)],
  exports: [RouterModule],
})
export class LoginRoutingModule {}
