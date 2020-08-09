import { Injectable } from "@angular/core";

import { GetAllResolver } from "@shared/resolver/generic.resolver";
import { Income } from '@app/core/model/income';
import { IncomeService } from '@app/core/service/income/income.service';

@Injectable()
export class IncomeResolver extends GetAllResolver<Income> {
  constructor(private service: IncomeService) {
    super(service);
  }
}
