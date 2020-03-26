import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { UserComponent } from '@app/user/user.component';
import { UserResolver } from './user.resolver';

const userRoutes: Routes = [
    { path: '',
      component: UserComponent,
      canActivate: [AuthGuard],
      resolve: {
        users: UserResolver
      }
    }
    //{ path: 'naoEncontrado', component: CursoNaoEncontradoComponent },
    //{ path: ':id', component: CursoDetalheComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UserRoutingModule {}
