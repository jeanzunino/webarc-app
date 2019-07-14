import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { UsersService } from '../../services/users.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  @Input() item: User;

  constructor(
    private route: ActivatedRoute,
    private service: UsersService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.get();
  }

  get(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.service.get(id)
      .subscribe(item => this.item = item);
  }

  goBack(): void {
    this.location.back();
  }

}
