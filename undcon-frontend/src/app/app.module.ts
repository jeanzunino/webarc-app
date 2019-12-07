import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { HttpClientModule } from '@angular/common/http';
import { FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppComponent } from '@app/app.component';
import { AppRoutingModule } from '@app/app-routing.module';
import { HomeComponent } from '@pages/home/home.component';
import { OutraComponent } from '@pages/outra/outra.component';
import { NavbarComponent } from '@pages/navbar/navbar.component';
import { PageNotFoundComponent } from '@pages/page-not-found/page-not-found.component';
import { LoginComponent } from '@pages/login/login.component';

import { StorageService } from '@services/storage/storage.service';
import { AuthService } from '@services/auth/auth.service';
import { AuthGuard } from '@guards/auth/auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    OutraComponent,
    HomeComponent,
    NavbarComponent,
    PageNotFoundComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot(),
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    FormBuilder,
    StorageService,
    AuthService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
