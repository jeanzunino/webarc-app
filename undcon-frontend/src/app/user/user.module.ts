import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';

import { UserComponent } from '@app/user/user.component';
import { UserRoutingModule } from '@app/user/user.routing.module';

@NgModule({
  declarations: [
    UserComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    UserRoutingModule
    //SharedModule
  ]
})
export class UserModule { }
