import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { SharedModule } from './../shared/shared.module';
import { NgModule } from "@angular/core";

import { PageNotFoundComponent } from "@app/page-not-found/page-not-found.component";

@NgModule({
  declarations: [PageNotFoundComponent],
  exports: [PageNotFoundComponent],
  imports: [SharedModule, MDBBootstrapModule.forRoot()]
})
export class PageNotFoundModule {}
