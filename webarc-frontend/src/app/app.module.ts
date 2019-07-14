import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { FormsModule }    from '@angular/forms';
import { HeroesComponent }      from './heroes/heroes.component';
import { MessagesComponent } from './messages/messages.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import { PeopleComponent } from './people/people.component';
import { UsersComponent } from './users/users.component';
import { PermissionsComponent } from './permissions/permissions.component';
import { ProductsComponent } from './products/products.component';
import { ServicesComponent } from './services/services.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  declarations: [
    AppComponent,
    HeroesComponent,
    MessagesComponent,
    HeroDetailComponent,
    PeopleComponent,
    UsersComponent,
    PermissionsComponent,
    ProductsComponent,
    ServicesComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
