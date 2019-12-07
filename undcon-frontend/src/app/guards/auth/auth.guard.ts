import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '@services/auth/auth.service';

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
    if (this.authService.getAuthenticatedUser()) {
      this.authService.displayMenuEmitter.emit(true);
      return true;
    }
    this.authService.displayMenuEmitter.emit(false);
    this.router.navigate(['/login'])
    return false;
  }
}
