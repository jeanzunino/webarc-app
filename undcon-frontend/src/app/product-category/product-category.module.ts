import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { ProductCategoryRoutingModule } from '@app/product-category/product-category.routing.module';
import { ProductCategoryComponent } from '@app/product-category/product-category.component';

@NgModule({
  declarations: [
    ProductCategoryComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    ProductCategoryRoutingModule
    //SharedModule
  ]
})
export class ProductCategoryModule { }
