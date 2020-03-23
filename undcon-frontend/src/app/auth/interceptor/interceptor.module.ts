import { NgModule, Injectable } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { StorageService } from '@app/core/service/storage/storage.service';
import { tap } from 'rxjs/operators';

@Injectable()
export class HttpsRequestInterceptor implements HttpInterceptor {
 
  constructor(private storageService: StorageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    const dupReq = req.clone({
      headers: req.headers
                .set('Content-Type', 'application/json')
                .set('Authorization', this.storageService.getUser() ? this.storageService.getUser().token : '')
    });
    return next.handle(dupReq)
            .pipe(tap(() => {}, 
                  err => {
                    console.log(this.handleErrors(err))
                  }));
  }

  handleErrors(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
        // client-side error
        errorMessage = `Error: ${error.error.message}`;
    } else {
        // server-side error
        errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
}
}

@NgModule({
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpsRequestInterceptor,
    multi: true,
  }],
})
export class InterceptorModule { }
