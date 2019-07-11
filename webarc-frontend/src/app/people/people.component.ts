import { Component, OnInit } from '@angular/core';

import { PeopleService } from '../people.service';
import { Person } from '../model/person';

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  selected: Person;

  list: Person[];

  constructor(private service: PeopleService) { }

  ngOnInit() {
    this.getHeroes();
  }

  onSelect(item: Person): void {
    this.selected = item;
  }

  getHeroes(): void {
    this.service.getAll()
        .subscribe(heroes => this.list = heroes);
  }

}
