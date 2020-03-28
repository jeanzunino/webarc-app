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

  onClickItem(item) {
    console.log(item)
  }

  //Campos padrões caso a tela não implemente esse método
  getFieldsOfTable() {
    return ['item.id' , 'item.name'];
  }
}
