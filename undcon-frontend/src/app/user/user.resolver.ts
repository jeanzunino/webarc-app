import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { User } from '@app/core/model/user';
import { UserService } from '@app/core/service/user/user.service';

@Injectable()
export class UserResolver implements Resolve<User[]> {
  constructor(private userService: UserService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.getUsers();
  }

  private async getUsers() {
    let users: User[];
    await this.userService.getUsers().toPromise().then(response => {
        users = response;
    })
    return users;
  }
}