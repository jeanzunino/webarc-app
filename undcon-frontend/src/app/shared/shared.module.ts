import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CollapseModule, MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';

import { DialogComponent } from '@component/dialog/dialog.component';

@NgModule({
  declarations: [
    DialogComponent
  ],
  exports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    CollapseModule,
    DialogComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot()
  ]
})
export class SharedModule { }