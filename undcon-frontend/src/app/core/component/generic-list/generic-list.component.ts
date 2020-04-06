import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { EntityService } from '@app/core/service/entity/entity.service';

export class GenericListComponent<T> implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private entityService: EntityService<T>,
    private activatedRoute: ActivatedRoute) { }

  items: T[];

  ngOnInit() {
    this.items = this.activatedRoute.snapshot.data.items;
  }

  getHeaderTitle() {
    return ['Id', 'Nome'];
  }

  //Campos padrões caso a tela não implemente esse método
  getFieldsOfTable(item, header) {
    switch (header) {
      case 'Id': {
        return item.id;
      }
      case 'Nome': {
        return item.name;
      }
      default: {
        return '';
      }
    }
  }
}
