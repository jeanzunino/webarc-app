import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { SharedModule } from './../shared/shared.module';
import { PageForbiddenComponent } from './page-forbidden.component';
import { NgModule } from "@angular/core";

@NgModule({
  declarations: [PageForbiddenComponent],
  exports: [PageForbiddenComponent],
  imports: [SharedModule, MDBBootstrapModule.forRoot()]
})
export class PageForbiddenModule {}
