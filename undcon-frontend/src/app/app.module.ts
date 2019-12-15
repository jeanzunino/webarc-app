import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from '@app/app.component';
import { AppRoutingModule } from '@app/app.routing.module';
import { LoginModule } from '@app/login/login.module';
import { PageNotFoundModule } from '@app/page-not-found/page-not-found.module';
import { UserModule } from '@app/user/user.module';
import { HomeModule } from '@app/home/home.module';
import { NavbarModule } from '@app/navbar/navbar.module';
import { StorageService } from '@services/storage/storage.service';
import { AuthService } from '@services/auth/auth.service';
import { AuthGuard } from '@guards/auth/auth.guard';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    PageNotFoundModule,
    UserModule,
    HomeModule,
    NavbarModule
  ],
  providers: [
    StorageService,
    AuthService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
