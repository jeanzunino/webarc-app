import { Injectable } from '@angular/core';

import { CostCenter } from '@app/core/model/cost-center';
import { CostCenterService } from '@app/core/service/cost-center/cost-center.service';
import { GetAllResolver } from '@shared/resolver/generic.resolver';

@Injectable()
export class CostCenterResolver extends GetAllResolver<CostCenter> {
  constructor(private service: CostCenterService) {
    super(service);
  }
}
