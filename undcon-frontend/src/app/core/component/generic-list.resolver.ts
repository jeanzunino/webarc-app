import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { User } from '@app/core/model/user';
import { UserService } from '@app/core/service/user/user.service';

export class GenericListResolver<T> implements Resolve<T[]> {
  constructor(private entityService: EntityService<T>) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.getAll();
  }

  private async getAll() {
    let itens: T[];
    await this.entityService.getAll().toPromise().then(response => {
        itens = response;
    })
    return itens;
  }
}
