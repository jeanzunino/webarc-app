import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { ProviderRoutingModule } from '@app/provider/provider.routing.module';
import { ProviderComponent } from '@app/provider/provider.component';

@NgModule({
  declarations: [
    ProviderComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    ProviderRoutingModule
    //SharedModule
  ]
})
export class ProviderModule { }
