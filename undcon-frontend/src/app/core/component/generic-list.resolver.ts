import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { EntityService } from '@app/core/service/entity/entity.service';

export class GenericListResolver<T> implements Resolve<T[]> {
  constructor(private entityService: EntityService<T>) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.getAll();
  }

  private async getAll() {
    return await this.entityService.getAll().toPromise();
  }
}
