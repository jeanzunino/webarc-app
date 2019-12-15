import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { SharedModule } from '@app/shared/shared.module';
import { UserComponent } from '@app/user/user.component';

@NgModule({
  declarations: [
    UserComponent
  ],
  imports: [
    MDBBootstrapModule.forRoot(),
    SharedModule
  ]
})
export class UserModule { }
