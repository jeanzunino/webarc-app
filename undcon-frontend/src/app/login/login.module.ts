import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { HttpClientModule } from '@angular/common/http';

import { SharedModule } from '@app/shared/shared.module';
import { LoginComponent } from '@app/login/login.component';
import { LoginRoutingModule } from '@app/login/login.routing.module';

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    SharedModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
    LoginRoutingModule
  ],
  bootstrap: [
    LoginComponent
  ]
})
export class LoginModule { }
