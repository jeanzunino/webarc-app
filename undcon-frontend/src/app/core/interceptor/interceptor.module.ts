import { NgxSpinnerService } from 'ngx-spinner';
import { NgModule, Injectable } from '@angular/core';
import { AuthService } from '@service/auth/auth.service';

import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';

import { StorageService } from '@core/service/storage/storage.service';
import { Router } from '@angular/router';

@Injectable()
export class HttpsRequestInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService,
              private storageService: StorageService,
              public toastr: ToastrService,
              private router: Router,
              public spinner: NgxSpinnerService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const dupReq = req.clone({
      headers: req.headers
        .set('Content-Type', 'application/json')
        .set(
          'Authorization',
          this.storageService.getUser()
            ? this.storageService.getUser().token
            : ''
        ),
    });
    return next.handle(dupReq).pipe(
      tap(
        () => {},
        (err) => {
          console.log(this.handleErrors(err));
        }
      )
    );
  }

  handleErrors(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      this.toastr.clear();
      // server-side error
      switch(error.status) {
        case 400:
          this.toastr.error(
            error.error.message,
            'Erro'
          );
          break;
        case 403:
          this.router.navigate(["/forbidden"]);
          break;
        case 401:
          this.toastr.error(
            error.error.message,
            'Erro'
            );
            this.authService.signout();
            break;
        default:
          errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      }
      this.spinner.hide();
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}

@NgModule({
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpsRequestInterceptor,
      multi: true,
    },
  ],
})
export class InterceptorModule {}
