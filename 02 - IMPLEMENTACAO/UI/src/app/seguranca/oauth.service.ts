import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OauthService {

  oauthTokenUrl = 'http://localhost:8080/oauth/token';

  constructor(
    private httpClient: HttpClient,
    private jwtHelperService: JwtHelperService
  ) { }

  async login(usuario: string, senha: string): Promise<void> {
    const headers = new HttpHeaders({
      Authorization: 'Basic d2ViOjM1ODU0QFNtMjM4JDQ3NGRz',
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    const body = `username=${usuario}&password=${senha}&grant_type=password`;

    return this.httpClient.post(this.oauthTokenUrl, body, { headers, withCredentials: true }).toPromise<any>().then(response => {
      this.armazenarToken(response.access_token);
    }).catch(response => {
      if (response.status === 400) {
        if (response.error.error === 'invalid_grant') {
          return Promise.reject('Usuário ou senha inválida');
        }

      }
      return Promise.reject(response);
    });
  }

  async obterNovoAccessToken(): Promise<void> {
    const headers = new HttpHeaders({
      Authorization: 'Basic d2ViOjM1ODU0QFNtMjM4JDQ3NGRz',
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    const body = `grant_type=refresh_token`;

    await this.httpClient.post(this.oauthTokenUrl, body, { headers, withCredentials: true }).toPromise<any>().then(response => {
      this.armazenarToken(response.access_token);
    });

    return Promise.resolve(null);
  }

  armazenarToken(token: any): void {
    localStorage.setItem('token', token);
  }

  jwtPayload(): any {
    const token = localStorage.getItem('token');

    return this.jwtHelperService.decodeToken(token);
  }

  isAccessTokenInvalido(): boolean {
    const token = localStorage.getItem('token');

    return !token || this.jwtHelperService.isTokenExpired(token);
  }

  codigoUsuario(): number {
    return this.jwtPayload().codigo;
  }

}
