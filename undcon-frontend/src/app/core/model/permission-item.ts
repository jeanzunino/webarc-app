import { Entity } from '@model/entity';
import { Permission } from './permission';
import { ResourceTypeEnum } from '../enum/resource-type-enum';

export class PermissionItem extends Entity {
  permission: Permission;
  resourceType: ResourceTypeEnum;
}
