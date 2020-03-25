import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { UserService } from '@service/user/user.service';
import { User } from '@model/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private userService: UserService) { }

  items: User[];

  ngOnInit() {
    this.userService.getUsers()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(items => {
      this.items = items;
    });
  }

  onClickItem(item) {
    console.log(item)
  }
}
