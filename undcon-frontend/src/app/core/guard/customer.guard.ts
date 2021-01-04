import { ResourceTypeEnum } from '@app/core/enum/resource-type-enum';
import { Injectable } from "@angular/core";

import { PodeAcessarGuard } from './pode-acessar.guard';

@Injectable()
export class CustomerGuard extends PodeAcessarGuard {
    res = ResourceTypeEnum.CUSTOMER;
}
