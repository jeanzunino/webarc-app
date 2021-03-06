import { NgModule, Injector } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {
  CollapseModule,
  MDBBootstrapModule,
  CheckboxModule,
  TooltipModule
} from 'angular-bootstrap-md';
import { CommonModule, DatePipe } from '@angular/common';
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
import { AutocompleteLibModule } from 'angular-ng-autocomplete';

import { DialogComponent } from '@component/dialog/dialog.component';
import { TableComponent } from '@component/table/table.component';
import { PanelComponent } from '@component/panel/panel.component';
import { SimplePanelComponent } from '@component/simple-panel/simple-panel.component';
import { ValidationComponent } from '@component/validation/validation.component';
import { EmptyComponent } from '@component/empty/empty.component';
import { EmptyChildrenComponent } from '@component/empty-children/empty-children.component';
import { ConfirmDialogComponent } from '@component/confirm-dialog/confirm-dialog.component';
import { ButtonGroupComponent } from '@component/button-group/button-group.component';
import { InstallmentDialogComponent } from '@component/installment-dialog/installment-dialog.component';
import { LocalizedDatePipe } from '@core/pipes/localized-date-pipe';


export let SharedInjector: Injector;

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    DialogComponent,
    TableComponent,
    PanelComponent,
    SimplePanelComponent,
    ValidationComponent,
    EmptyComponent,
    EmptyChildrenComponent,
    ConfirmDialogComponent,
    ButtonGroupComponent,
    InstallmentDialogComponent
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
    SimplePanelComponent,
    CheckboxModule,
    ValidationComponent,
    EmptyComponent,
    EmptyChildrenComponent,
    AutocompleteLibModule,
    ButtonGroupComponent,
    TooltipModule
  ],
  imports: [
    CommonModule,
    FormsModule,
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
    MaskPipe,
    DatePipe,
    LocalizedDatePipe
  ]
})
export class SharedModule {
  constructor(private translate: TranslateService, private injector: Injector) {
    this.translate.setDefaultLang('pt-BR');
    SharedInjector = this.injector;
  }
}
