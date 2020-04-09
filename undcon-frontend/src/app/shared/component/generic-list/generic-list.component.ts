import { OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { EntityService } from '@service/entity/entity.service';

export class GenericListComponent<T> implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private entityService: EntityService<T>,
    private activatedRoute: ActivatedRoute) { }

  items: T[];
  isTheFilterPanelExpanded = true;

  ngOnInit() {
    this.items = this.activatedRoute.snapshot.data.items;
  }

  getHeaderTitle() {
    return ['Id', 'Nome'];
  }

  onClickItem(item) {
    alert(item)
  }

  updateExpandedFlagPanel() {
    this.isTheFilterPanelExpanded = !this.isTheFilterPanelExpanded;
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
