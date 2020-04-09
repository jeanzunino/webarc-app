import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { ProductCategoryRoutingModule } from '@app/product-category/product-category.routing.module';
import { ProductCategoryComponent } from '@app/product-category/product-category.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [
    ProductCategoryComponent
  ],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    ProductCategoryRoutingModule
    //SharedModule
  ]
})
export class ProductCategoryModule { }