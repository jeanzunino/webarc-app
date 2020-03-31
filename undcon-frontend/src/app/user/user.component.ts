import { Component, OnInit, OnDestroy } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '@service/user/user.service';
import { User } from '@model/user';
import { GenericListComponent } from '@component-generic-list/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: '../core/component/generic-list/generic-list.component.html',
  styleUrls: ['../core/component/generic-list/generic-list.component.scss']
})
export class UserComponent extends GenericListComponent<User> {

  constructor(private service: UserService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }

  getHeaderTitle() {
    return ['Id', 'Login'];
  }

  //Campos padrões caso a tela não implemente esse método
  getFieldsOfTable(item, header) {
    switch (header) {
      case 'Id': {
        return item.id;
      }
      case 'Login': {
        return item.login;
      }
      default: {
        return '';
      }
    }
  }
}
