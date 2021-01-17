import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";

import { UserService } from '@service/user/user.service';
import { ResourceTypeEnum } from '@core/enum/resource-type-enum';

@Injectable()
export class PodeAcessarGuard implements CanActivate {

    public res: ResourceTypeEnum;

    constructor(private router: Router,
                private userService: UserService) {}

    async canActivate() {
        const permissions = await this.userService.getPermissionOfLoggeduser().toPromise();

        if (permissions.find(p => this.res == p)) {
            return true;
        }

        this.router.navigate(["/forbidden"]);
        return false;
    }
}
