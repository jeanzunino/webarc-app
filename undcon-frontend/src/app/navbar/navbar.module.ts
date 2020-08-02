import { NgModule } from "@angular/core";
import { MDBBootstrapModule } from "angular-bootstrap-md";

import { NavbarComponent } from "@app/navbar/navbar.component";
import { NavbarRoutingModule } from "@app/navbar/navbar.routing.module";
import { SharedModule } from "@shared/shared.module";

@NgModule({
  declarations: [NavbarComponent],
  imports: [SharedModule, MDBBootstrapModule.forRoot(), NavbarRoutingModule],
  exports: [NavbarComponent],
})
export class NavbarModule {}
