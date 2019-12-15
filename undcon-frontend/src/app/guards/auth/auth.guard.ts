import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable, interval } from 'rxjs';

import { AuthService } from '@services/auth/auth.service';

import { PageEnum } from '@app/core/page-enum'

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService, 
    private router: Router
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot, 
    state: RouterStateSnapshot
  ) : Observable<boolean> | boolean {
    
    if (this.hasModule(state)) {
      if (this.authService.getAuthenticatedUser()) {
        this.authService.displayMenuEmitter.emit(true);
        return true;
      }

      this.authService.displayMenuEmitter.emit(false);
      this.router.navigate(['/login'])
      return false;
    }
    this.authService.displayMenuEmitter.emit(false);
    return true;
  }

  private hasModule(state: RouterStateSnapshot) {
    let pages = Object.values(PageEnum);
    let hasModule = false;
    for (let indexModule = 0; indexModule < pages.length; indexModule++) {
      if (pages[indexModule] === state.url) {
        hasModule = true;
        break;
      }
    }
    return hasModule;
  }
}
