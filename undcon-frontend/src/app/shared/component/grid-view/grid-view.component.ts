import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

import { EntityService } from '@service/entity/entity.service';
import { Page } from '@model/page';
import { SharedInjector } from '@shared/shared.module';

export class GridViewComponent<T> implements OnInit {

  private spinner = SharedInjector.get(NgxSpinnerService);

  items: Page<T>;

  constructor(private service: EntityService<T>,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.items = this.activatedRoute.snapshot.data.items as Page<T>;
    this.spinner.hide();
  }

  async reloadItems(page) {
    this.spinner.show()
    this.items = await this.service.getAll(page).toPromise() as Page<T>;
    this.spinner.hide()
  }

  onClickItem(item) {
    alert(item);
  }

}
