import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { ProviderRoutingModule } from '@app/provider/provider.routing.module';
import { ProviderComponent } from '@app/provider/provider.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [
    ProviderComponent
  ],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    ProviderRoutingModule
  ]
})
export class ProviderModule { }