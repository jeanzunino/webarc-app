import { NgModule } from '@angular/core';

import { HomeComponent } from '@app/home/home.component';
import { HomeRoutingModule } from '@app/home/home.routing.module';

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    HomeRoutingModule
  ]
})
export class HomeModule { }
