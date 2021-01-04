import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@guard/auth/auth.guard';
import { PdvComponent } from '@app/pdv/pdv.component';
import { PdvResolver } from '@app/pdv/pdv.resolver';

const routes: Routes = [
    { path: '',
      component: PdvComponent,
      canActivate: [AuthGuard],
      resolve: {
        items: PdvResolver
      }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    providers: [PdvResolver]
})
export class PdvRoutingModule {}
