import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { PurchaseRoutingModule } from '@app/purchase/purchase.routing.module';
import { PurchaseComponent } from '@app/purchase/purchase.component';

@NgModule({
  declarations: [
    PurchaseComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    PurchaseRoutingModule
    //SharedModule
  ]
})
export class PurchaseModule { }
