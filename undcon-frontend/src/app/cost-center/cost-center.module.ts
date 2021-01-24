import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxMaskModule } from 'ngx-mask';

import { CostCenterRoutingModule } from '@app/cost-center/cost-center.routing.module';
import { CostCenterComponent } from '@app/cost-center/cost-center.component';
import { CostCenterEditComponent } from '@app/cost-center/cost-center-edit/cost-center-edit.component';
import { SharedModule } from '@shared/shared.module';
import { CostCenterGuard } from '@guard/cost-center.guard';

@NgModule({
  declarations: [CostCenterComponent, CostCenterEditComponent],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    CostCenterRoutingModule,
    NgxMaskModule.forRoot()
  ],
  providers: [
    CostCenterGuard
  ]
})
export class CostCenterModule {}
