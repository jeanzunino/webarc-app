import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { SharedModule } from '@shared/shared.module';
import { ServiceOrderEditComponent } from './service-order-edit.component';

@NgModule({
  declarations: [ServiceOrderEditComponent],
  imports: [SharedModule, MDBBootstrapModule.forRoot()]
})
export class ServiceOrderEditModule {}
