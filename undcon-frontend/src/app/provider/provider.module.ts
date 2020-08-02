import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxMaskModule } from 'ngx-mask';

import { ProviderRoutingModule } from '@app/provider/provider.routing.module';
import { ProviderComponent } from '@app/provider/provider.component';
import { ProviderEditComponent } from '@app/provider/provider-edit/provider-edit.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [ProviderComponent, ProviderEditComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    ProviderRoutingModule,
    NgxMaskModule.forRoot()
  ],
})
export class ProviderModule {}
