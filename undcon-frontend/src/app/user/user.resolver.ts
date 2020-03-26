import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { User } from '@app/core/model/user';
import { UserService } from '@app/core/service/user/user.service';
import { GenericListResolver } from '@app/core/component/generic-list.resolver';


@Injectable()
export class UserResolver extends GenericListResolver<User> {
  constructor(private userService: UserService) {
    super(userService)
  }
}
