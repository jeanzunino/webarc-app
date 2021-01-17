import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { ProductRoutingModule } from '@app/product/product.routing.module';
import { ProductComponent } from '@app/product/product.component';
import { SharedModule } from '@shared/shared.module';
import { ProductEditComponent } from '@app/product/product-edit/product-edit.component';
import { ProductGuard } from '@guard/product.guard';

@NgModule({
  declarations: [ProductComponent, ProductEditComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    ProductRoutingModule
  ],
  providers: [
    ProductGuard
  ]
})
export class ProductModule {}
