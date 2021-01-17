import { ResourceTypeEnum } from '@app/core/enum/resource-type-enum';
import { Injectable } from "@angular/core";

import { PodeAcessarGuard } from './generic.guard';

@Injectable()
export class ProductCategoryGuard extends PodeAcessarGuard {
    res = ResourceTypeEnum.PRODUCT_CATEGORY;
}
