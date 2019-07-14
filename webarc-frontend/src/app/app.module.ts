import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule }    from '@angular/forms';
import { MessagesComponent } from './messages/messages.component';

import { PeopleComponent } from './people/people.component';
import { UsersComponent } from './users/users.component';
import { PermissionsComponent } from './permissions/permissions.component';
import { ProductsComponent } from './products/products.component';
import { ServiceTypesComponent } from './service-types/service-types.component';

import { PersonDetailComponent } from './people/person-detail/person-detail.component';
import { ProductDetailComponent } from './products/product-detail/product-detail.component';
import { ServiceTypeDetailComponent } from './service-types/service-type-detail/service-type-detail.component';
import { UserDetailComponent } from './users/user-detail/user-detail.component';
import { PermissionDetailComponent } from './permissions/permission-detail/permission-detail.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    MessagesComponent,
    PersonDetailComponent,
    PeopleComponent,
    UsersComponent,
    PermissionsComponent,
    ProductsComponent,
    ServiceTypesComponent,
    PersonDetailComponent,
    ProductDetailComponent,
    ServiceTypeDetailComponent,
    UserDetailComponent,
    PermissionDetailComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
