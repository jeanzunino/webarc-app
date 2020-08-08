import { Injectable } from "@angular/core";

import { BankCheck } from "@app/core/model/bank-check";
import { BankCheckService } from "@app/core/service/bank-check/bank-check.service";
import { GetAllResolver } from "@shared/resolver/generic.resolver";

@Injectable()
export class BankCheckResolver extends GetAllResolver<BankCheck> {
  constructor(private service: BankCheckService) {
    super(service);
  }
}
