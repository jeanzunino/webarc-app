import { Component, OnInit, OnDestroy } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { UserService } from '@service/user/user.service';
import { User } from '@model/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit, OnDestroy {

  private ngUnsubscribe = new Subject();

  constructor(private userService: UserService) { }

  users: User[];

  ngOnInit() {
    this.userService.getUsers()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(users => {
      this.users = users;
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  teste(user) {
    console.log(user)
  }
}
