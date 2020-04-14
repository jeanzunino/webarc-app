import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { UserService } from '@app/core/service/user/user.service';
import { Table, TableFieldCustomizationEmitter } from '@app/shared/model/table';
import { Permission } from '@app/core/model/permission';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
}