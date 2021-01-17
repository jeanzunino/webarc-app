import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import { AuthService } from '@service/auth/auth.service';
import { ResourceTypeEnum } from '@app/core/enum/resource-type-enum';
import { UserService } from '@app/core/service/user/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {

  permissions: ResourceTypeEnum[];

  constructor(
    private authService: AuthService,
    private userService: UserService,
    public translate: TranslateService
  ) {
    this.loadPermissions();
  }

  logout() {
    this.authService.signout();
  }

  async loadPermissions(){
    this.permissions = await this.userService.getPermissionOfLoggeduser().toPromise();
  }

  public verifyPermissionMenu(resource: string) {
    if(!this.permissions) {
      return false;
    }
    return this.permissions.find(p => p.toString() === resource) != null;
  }

  public verifyPermissionMainMenu(mainMenu: string) {
    switch(mainMenu) {
      case 'compra':
        return this.verifyPermissionMenu('PROVIDER') || this.verifyPermissionMenu('PURCHASE');
      case 'venda':
        return this.verifyPermissionMenu('CUSTOMER') || this.verifyPermissionMenu('SALE');
      case 'financeiro':
        return this.verifyPermissionMenu('BACK_CHECK') || this.verifyPermissionMenu('INCOME') || this.verifyPermissionMenu('EXPENSE');
      case 'geral':
        return this.verifyPermissionMenu('SERVICE_TYPE') || this.verifyPermissionMenu('PRODUCT_CATEGORY') || this.verifyPermissionMenu('PRODUCT');
      case 'configuracao':
        return this.verifyPermissionMenu('TENANT') || this.verifyPermissionMenu('PERMISSION')
          || this.verifyPermissionMenu('EMPLOYEE') || this.verifyPermissionMenu('USER');
      default:
        return false;
    }
  }
}
