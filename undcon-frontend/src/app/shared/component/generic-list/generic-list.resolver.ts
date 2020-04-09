import { Resolve, ActivatedRouteSnapshot } from '@angular/router';

import { EntityService } from '@service/entity/entity.service';

export class GenericListResolver<T> implements Resolve<T[]> {
  constructor(private entityService: EntityService<T>) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.getAll();
  }

  private async getAll() {
    return await this.entityService.getAll().toPromise();
  }
}