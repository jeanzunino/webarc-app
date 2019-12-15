import { Component, OnInit } from '@angular/core';
import { UserService } from '@app/services/user/user.service';
import { User } from '@app/models/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  constructor(private userService: UserService) { }

  users: User[];

  ngOnInit() {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }

  teste(user) {
    console.log(user)
  }
}
