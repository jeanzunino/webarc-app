import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

import { EntityService } from '@service/entity/entity.service';
import { AppInjector } from '@app/app.component';

export class GenericListComponent<T> implements OnInit {

  private spinner = AppInjector.get(NgxSpinnerService);

  constructor(private entityService: EntityService<T>,
              private activatedRoute: ActivatedRoute) { }

  items: T[];
  isTheFilterPanelExpanded = true;

  ngOnInit() {
    this.items = this.activatedRoute.snapshot.data.items;
    this.spinner.hide();
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
