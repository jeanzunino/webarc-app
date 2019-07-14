import { Component, OnInit } from '@angular/core';

import { UsersService } from '../services/users.service';
import { User } from '../model/user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  selected: User;

  list: User[];

  constructor(private service: UsersService) { }

  ngOnInit() {
    this.getHeroes();
  }

  onSelect(item: User): void {
    this.selected = item;
  }

  getHeroes(): void {
    this.service.getAll()
        .subscribe(heroes => this.list = heroes);
  }

}
