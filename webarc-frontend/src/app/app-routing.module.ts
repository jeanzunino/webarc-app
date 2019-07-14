import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';

import { PeopleComponent }  from './people/people.component';
import { PersonDetailComponent }  from './people/person-detail/person-detail.component';

import { PermissionsComponent }  from './permissions/permissions.component';
import { PermissionDetailComponent }  from './permissions/permission-detail/permission-detail.component';

import { ProductsComponent }  from './products/products.component';
import { ProductDetailComponent }  from './products/product-detail/product-detail.component';

import { UsersComponent }  from './users/users.component';
import { UserDetailComponent }  from './users/user-detail/user-detail.component';

import { ServiceTypesComponent }  from './service-types/service-types.component';
import { ServiceTypeDetailComponent }  from './service-types/service-type-detail/service-type-detail.component';

const routes: Routes = [

  { path: '', redirectTo: '/people', pathMatch: 'full' },

  { path: 'people', component: PeopleComponent },
  { path: 'people/:id', component: PersonDetailComponent },

  { path: 'permissions', component: PermissionsComponent },
  { path: 'permissions/:id', component: PermissionDetailComponent },

  { path: 'products', component: ProductsComponent },
  { path: 'products/:id', component: ProductDetailComponent },

  { path: 'users', component: UsersComponent },
  { path: 'users/:id', component: UserDetailComponent },

  { path: 'service-types', component: ServiceTypesComponent },
  { path: 'service-types/:id', component: ServiceTypeDetailComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule {

}
