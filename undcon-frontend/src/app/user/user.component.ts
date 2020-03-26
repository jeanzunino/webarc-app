import { Component, OnInit, OnDestroy } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '@service/user/user.service';
import { User } from '@model/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit, OnDestroy {

  private ngUnsubscribe = new Subject();

  constructor(private userService: UserService,
              private activatedRoute: ActivatedRoute) { }

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
