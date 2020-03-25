import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { UserRoutingModule } from '@app/user/user.routing.module';
import { UserComponent } from '@app/user/user.component';

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
