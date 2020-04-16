import { Resolve} from '@angular/router';

import { EntityService } from '@service/entity/entity.service';

export class GetAllResolver<T> implements Resolve<any> {

  constructor(private entityService: EntityService<T>) {}

  resolve() {
    return this.getAll();
  }

  private async getAll() {
    return await this.entityService.getAll().toPromise();
  }

}
