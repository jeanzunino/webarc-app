import { ResourceTypeEnum } from '@app/core/enum/resource-type-enum';
import { Injectable } from "@angular/core";
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from "@angular/router";
import { Observable } from "rxjs";

import { AuthService } from "@service/auth/auth.service";

@Injectable()
export class PodeAcessarGuard implements CanActivate {

    public res: ResourceTypeEnum;

    constructor(private router: Router) {}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<boolean> | boolean {
        debugger
        const permissions = JSON.parse(localStorage.getItem('permissions')) as ResourceTypeEnum[];
        
        if (permissions.find(p => this.res == p)) {
            return true;
        }

        this.router.navigate(["/forbidden"]);
        return false;
    }
}
