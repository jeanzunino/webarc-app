import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

import { EntityService } from '@service/entity/entity.service';
import { SharedInjector } from '@app/shared/shared.module';
import { Page } from '@model/page';

export class GridViewComponent<T> implements OnInit {

  items: Page<T>;

  constructor(private spinner: NgxSpinnerService,
              private service: EntityService<T>,
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
