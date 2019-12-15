import { NgModule } from '@angular/core';

import { NavbarComponent } from '@app/navbar/navbar.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

@NgModule({
  declarations: [
    NavbarComponent
  ],
  imports: [
    MDBBootstrapModule.forRoot()
  ],
  exports: [
    NavbarComponent
  ]
})
export class NavbarModule { }
