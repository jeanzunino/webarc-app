import { Injectable } from '@angular/core';

import { User } from '@model/user';
import { UserService } from '@service/user/user.service';
import { GenericListResolver } from '@component/generic-list/generic-list.resolver';


@Injectable()
export class UserResolver extends GenericListResolver<User> {
  constructor(private userService: UserService) {
    super(userService)
  }
}