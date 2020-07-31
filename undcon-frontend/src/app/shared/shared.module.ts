import { NgModule, Injector } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {
  CollapseModule,
  MDBBootstrapModule,
  CheckboxModule,
} from 'angular-bootstrap-md';
import { CommonModule } from '@angular/common';
import { NgxSpinnerModule } from 'ngx-spinner';
import {
  TranslateModule,
  TranslateService,
  TranslateLoader,
} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HttpClient } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgxMaskModule, MaskPipe } from 'ngx-mask';
import { RouterModule } from '@angular/router';

import { DialogComponent } from '@component/dialog/dialog.component';
import { TableComponent } from '@component/table/table.component';
import { PanelComponent } from '@component/panel/panel.component';
import { ValidationComponent } from '@component/validation/validation.component';
import { EmptyComponent } from '@component/empty/empty.component';
import { EmptyChildrenComponent } from '@component/empty-children/empty-children.component';

export let SharedInjector: Injector;

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    DialogComponent,
    TableComponent,
    PanelComponent,
    ValidationComponent,
    EmptyComponent,
    EmptyChildrenComponent
  ],
  exports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    CollapseModule,
    NgxSpinnerModule,
    DialogComponent,
    TranslateModule,
    NgxPaginationModule,
    TableComponent,
    PanelComponent,
    CheckboxModule,
    ValidationComponent,
    EmptyComponent,
    EmptyChildrenComponent
  ],
  imports: [
    CommonModule,
    NgxSpinnerModule,
    MDBBootstrapModule.forRoot(),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
    NgxPaginationModule,
    NgxMaskModule.forRoot(),
    RouterModule
  ],
  providers: [
    MaskPipe
  ]
})
export class SharedModule {
  constructor(private translate: TranslateService, private injector: Injector) {
    this.translate.setDefaultLang('pt-BR');
    SharedInjector = this.injector;
  }
}
