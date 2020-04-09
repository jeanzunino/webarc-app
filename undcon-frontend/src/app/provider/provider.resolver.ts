import { Injectable } from '@angular/core';

import { Provider } from '@model/provider';
import { ProviderService } from '@service/provider/provider.service';
import { GenericListResolver } from '@component/generic-list/generic-list.resolver';


@Injectable()
export class ProviderResolver extends GenericListResolver<Provider> {
  constructor(service: ProviderService) {
    super(service)
  }
}