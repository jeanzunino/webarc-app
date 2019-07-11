import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { HeroesComponent }      from './heroes/heroes.component';
import { HeroDetailComponent }  from './hero-detail/hero-detail.component';

import { PeopleComponent }  from './people/people.component';
import { PermissionsComponent }  from './permissions/permissions.component';
import { ProductsComponent }  from './products/products.component';
import { ServicesComponent }  from './services/services.component';
import { UsersComponent }  from './users/users.component';

const routes: Routes = [
//Exemplo do Angular
  { path: '', redirectTo: '/heroes', pathMatch: 'full' },
  { path: 'detail/:id', component: HeroDetailComponent },
  { path: 'heroes', component: HeroesComponent } ,


  { path: 'people', component: PeopleComponent },
  { path: 'permissions', component: PermissionsComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'services', component: ServicesComponent },
  { path: 'users', component: UsersComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule {

}
