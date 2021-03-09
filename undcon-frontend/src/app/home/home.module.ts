import { NgModule } from "@angular/core";
import { MDBBootstrapModule } from "angular-bootstrap-md";

import { HomeComponent } from "@app/home/home.component";
import { HomeRoutingModule } from "@app/home/home.routing.module";
import { SharedModule } from "@shared/shared.module";
import { SlickCarouselModule } from "ngx-slick-carousel";


@NgModule({
  declarations: [HomeComponent],
  imports: [SharedModule, MDBBootstrapModule.forRoot(), HomeRoutingModule, SlickCarouselModule],
})
export class HomeModule {}
