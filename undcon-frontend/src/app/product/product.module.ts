import { NgModule } from "@angular/core";
import { MDBBootstrapModule } from "angular-bootstrap-md";

import { ProductRoutingModule } from "@app/product/product.routing.module";
import { ProductComponent } from "@app/product/product.component";
import { SharedModule } from "@shared/shared.module";

@NgModule({
  declarations: [ProductComponent],
  imports: [SharedModule, MDBBootstrapModule.forRoot(), ProductRoutingModule],
})
export class ProductModule {}
