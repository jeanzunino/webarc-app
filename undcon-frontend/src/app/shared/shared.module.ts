import { NgModule, Injector } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CollapseModule, MDBBootstrapModule } from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { NgxSpinnerModule } from "ngx-spinner";
import { TranslateModule, TranslateService, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HttpClient } from '@angular/common/http';

import { DialogComponent } from '@component/dialog/dialog.component';

export let SharedInjector: Injector;

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

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
    DialogComponent,
    TranslateModule
  ],
  imports: [
    CommonModule,
    NgxSpinnerModule,
    MDBBootstrapModule.forRoot(),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ]
})
export class SharedModule {

  constructor(private translate: TranslateService,
              private injector: Injector) {
    this.translate.setDefaultLang('pt-BR');
    SharedInjector = this.injector
  }
}