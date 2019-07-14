import { Component, OnInit } from '@angular/core';

import { ServiceTypesService } from '../services/service-types.service';
import { Service } from '../model/service';

@Component({
  selector: 'app-service-types',
  templateUrl: './service-types.component.html',
  styleUrls: ['./service-types.component.css']
})
export class ServiceTypesComponent implements OnInit {

  selected: Service;

  list: Service[];

  constructor(private service: ServiceTypesService) { }

  ngOnInit() {
    this.getHeroes();
  }

  onSelect(item: Service): void {
    this.selected = item;
  }

  getHeroes(): void {
    this.service.getAll()
        .subscribe(heroes => this.list = heroes);
  }
}
