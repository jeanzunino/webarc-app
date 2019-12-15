import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { HttpClientModule } from '@angular/common/http';

import { LoginComponent } from '@app/login/login.component';
import { AppComponent } from '@app/app.component';
import { SharedModule } from '@app/shared/shared.module';

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    SharedModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot()
  ],
  bootstrap: [AppComponent]
})
export class LoginModule { }
