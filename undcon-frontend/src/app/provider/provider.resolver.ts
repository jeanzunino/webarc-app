import { Injectable } from '@angular/core';

import { Provider } from '@model/provider';
import { ProviderService } from '@service/provider/provider.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';


@Injectable()
export class ProviderResolver extends GetAllResolver<Provider> {
  constructor(service: ProviderService) {
    super(service)
  }
}