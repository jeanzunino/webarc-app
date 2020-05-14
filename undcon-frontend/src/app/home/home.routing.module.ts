import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "@app/home/home.component";
import { AuthGuard } from "@guard/auth/auth.guard";

const homeRoutes: Routes = [
  { path: "", component: HomeComponent, canActivate: [AuthGuard] },
  //{ path: 'naoEncontrado', component: CursoNaoEncontradoComponent },
  //{ path: ':id', component: CursoDetalheComponent }
];

@NgModule({
  imports: [RouterModule.forChild(homeRoutes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
