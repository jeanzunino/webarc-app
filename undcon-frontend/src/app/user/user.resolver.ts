import { Injectable } from '@angular/core';

import { User } from '@model/user';
import { UserService } from '@service/user/user.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';

@Injectable()
export class UserResolver extends GetAllResolver<User> {
  constructor(private userService: UserService) {
    super(userService);
  }
}
