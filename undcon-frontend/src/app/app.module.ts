
import { NgModule } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { AppComponent } from '@app/app.component';
import { PageNotFoundModule } from '@app/page-not-found/page-not-found.module';
import { NavbarModule } from '@app/navbar/navbar.module';
import { StorageService } from '@service/storage/storage.service';
import { AuthService } from '@service/auth/auth.service';
import { AuthGuard } from '@guard/auth/auth.guard';
import { SharedModule } from '@shared/shared.module';
import { LoginModule } from '@app/login/login.module';
import { InterceptorModule } from '@interceptor/interceptor.module';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    SharedModule,
    HttpClientModule,
    PageNotFoundModule,
    NavbarModule,
    LoginModule,
    InterceptorModule,
    ToastrModule.forRoot(),
    RouterModule,
    TranslateModule.forRoot({
      loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
      }
    }),
    BrowserAnimationsModule
  ],
  providers: [
    StorageService,
    AuthService,
    AuthGuard
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }