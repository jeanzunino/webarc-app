import { ResourceTypeEnum } from './../../core/enum/resource-type-enum';
import { Component } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { MDBModalRef, ModalOptions } from "angular-bootstrap-md";
import { ToastrService } from "ngx-toastr";
import { TranslateService } from "@ngx-translate/core";

import { Permission } from "@model/permission";
import { PermissionService } from "@service/permission/permission.service";
import { DefaultEditViewComponent } from "@component/default-edit-view/default-edit-view.component";
import { PermissionItem } from '@model/permission-item';

@Component({
  selector: "app-permission-edit",
  templateUrl: "./permission-edit.component.html"
})
export class PermissionEditComponent extends DefaultEditViewComponent<
  Permission
> {

  private permission: Permission;
  public resourceTypeEnumLista: ResourceTypeEnum[];
  public resourceTypePermissionItem: ResourceTypeEnum[] = [];

  constructor(
    public permissionModalRef: MDBModalRef,
    modalOptions: ModalOptions,
    toastr: ToastrService,
    translate: TranslateService,
    public service: PermissionService
  ) {
    super(permissionModalRef, modalOptions, toastr, translate, service);
  }

  async loadPage() {
    if (this.permission) {
      await this.service.getPermissionItems(this.permission.id).toPromise()
        .then(pi => pi.forEach(val => this.resourceTypePermissionItem.push(val.resourceType)));
    }

    this.resourceTypeEnumLista = await this.service.getPermissionItemsResource().toPromise();
  }

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(null),
      name: new FormControl("", Validators.required),
    });
  }

  onLoadValuesEdit(per: Permission) {
    this.permission = per;
    this.getFormGroup().patchValue({
      id: per.id,
      name: per.name
    });
  }

  get nameForm() {
    return this.getFormGroup().get("name");
  }

  change(checked: boolean, res: ResourceTypeEnum) {
    if (checked) {
      const pi = new PermissionItem();
      pi.permission = this.permission;
      pi.resourceType = res;
      this.service.addPermissionItem(this.permission.id, pi).subscribe();
    } else {
      this.service.deletePermissionItem(this.permission.id, res).subscribe();
    }
  }

  getEnumTranslate(res: ResourceTypeEnum) {
    return this.translate.instant(`enums.resource-type.${res}`);
  }

  permissionChecked(res: ResourceTypeEnum) {
    return this.resourceTypePermissionItem.find(r => r == res);
  }
}
