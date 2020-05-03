import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { PdvRoutingModule } from '@app/pdv/pdv.routing.module';
import { PdvComponent } from '@app/pdv/pdv.component';
import { OpenPdvComponent } from '@app/pdv/open-pdv/open-pdv.component';
import { ClosePdvComponent } from '@app/pdv/close-pdv/close-pdv.component';
import { ResumePdvComponent } from '@app/pdv/resume-pdv/resume-pdv.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [
    PdvComponent,
    OpenPdvComponent,
    ClosePdvComponent,
    ResumePdvComponent
  ],
  imports: [
    SharedModule,
    MDBBootstrapModule.forRoot(),
    PdvRoutingModule
  ]
})
export class PdvModule { }
