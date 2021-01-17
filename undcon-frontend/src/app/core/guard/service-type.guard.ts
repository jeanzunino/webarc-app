import { ResourceTypeEnum } from '@app/core/enum/resource-type-enum';
import { Injectable } from "@angular/core";

import { PodeAcessarGuard } from './generic.guard';

@Injectable()
export class ServiceTypeGuard extends PodeAcessarGuard {
    res = ResourceTypeEnum.SERVICE_TYPE;
}
