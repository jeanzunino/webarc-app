
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';

import { AppComponent } from '@app/app.component';
import { AppRoutingModule } from '@app/app.routing.module';
import { PageNotFoundModule } from '@app/page-not-found/page-not-found.module';
import { NavbarModule } from '@app/navbar/navbar.module';
import { StorageService } from '@service/storage/storage.service';
import { AuthService } from '@service/auth/auth.service';
import { AuthGuard } from '@guard/auth/auth.guard';
import { SharedModule } from '@app/shared/shared.module';
import { LoginModule } from '@app/login/login.module';
import { InterceptorModule } from './auth/interceptor/interceptor.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    SharedModule,
    AppRoutingModule,
    HttpClientModule,
    PageNotFoundModule,
    NavbarModule,
    LoginModule,
    InterceptorModule,
    ToastrModule.forRoot()
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
