import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

import { Provider } from '@app/core/model/provider';
import { ProviderService } from '@app/core/service/provider/provider.service';
import { GenericListResolver } from '@app/core/component/generic-list.resolver';


@Injectable()
export class ProviderResolver extends GenericListResolver<Provider> {
  constructor(private service: ProviderService) {
    super(service)
  }
}
