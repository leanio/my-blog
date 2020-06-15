import { HttpClient, HttpRequest, HttpEvent, HttpHandler, HttpInterceptor } from '@angular/common/http';
import { from as fromPromise, Observable} from 'rxjs';
import { Injectable } from '@angular/core';
import { OauthService } from './oauth.service';


@Injectable({
  providedIn: 'root'
})
export class BlogHttpInterceptor implements HttpInterceptor {

  constructor(
    private auth: OauthService
  ) {  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url !== this.auth.oauthTokenUrl && this.auth.isAccessTokenInvalido()) {
      return fromPromise(this.handle(req, next));
    }

    return next.handle(req);
  }

  async handle(req: HttpRequest<any>, next: HttpHandler): Promise<HttpEvent<any>> {
    await this.auth.obterNovoAccessToken();

    const authReq = req.clone({
      setHeaders: {
        Authorization: 'Bearer ' + localStorage.getItem('token')
      }
    });

    return next.handle(authReq).toPromise();
  }

}
