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
}
