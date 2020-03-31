import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { SaleRoutingModule } from '@app/sale/sale.routing.module';
import { SaleComponent } from '@app/sale/sale.component';

@NgModule({
  declarations: [
    SaleComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    SaleRoutingModule
    //SharedModule
  ]
})
export class SaleModule { }
