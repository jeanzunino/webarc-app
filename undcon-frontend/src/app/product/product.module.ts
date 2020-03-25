import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { ProductRoutingModule } from '@app/product/product.routing.module';
import { ProductComponent } from '@app/product/product.component';

@NgModule({
  declarations: [
    ProductComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    ProductRoutingModule
    //SharedModule
  ]
})
export class ProductModule { }
