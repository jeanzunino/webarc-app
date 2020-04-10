import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CollapseModule, MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { NgxSpinnerModule } from "ngx-spinner";

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
    NgxSpinnerModule,
    DialogComponent
  ],
  imports: [
    CommonModule,
    NgxSpinnerModule,
    MDBBootstrapModule.forRoot()
  ]
})
export class SharedModule { }