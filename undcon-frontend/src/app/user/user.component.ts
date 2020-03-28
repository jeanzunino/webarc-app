import { Component, OnInit, OnDestroy } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '@service/user/user.service';
import { User } from '@model/user';
import { GenericListComponent } from '@app/core/component/generic-list.component';

@Component({
  selector: 'app-generic-list',
  templateUrl: './user.component.html',
  styleUrls: ['../core/component/generic-list.component.scss']
})
export class UserComponent extends GenericListComponent<User> {

  constructor(private service: UserService,
              activatedRoute: ActivatedRoute) {
      super(service, activatedRoute)
  }

  getFieldsOfTable() {
    return ['login'];
  }
}
