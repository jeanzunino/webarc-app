import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { UserRoutingModule } from '@app/user/user.routing.module';
import { UserComponent } from '@app/user/user.component';
import { UserEditComponent } from '@app/user/user-edit/user-edit.component';
import { SharedModule } from '@shared/shared.module';
import { UserGuard } from '@guard/user.guard';

@NgModule({
  declarations: [UserComponent, UserEditComponent],
  imports: [SharedModule, MDBBootstrapModule.forRoot(), UserRoutingModule],
  providers: [
    UserGuard
  ]
})
export class UserModule {}
