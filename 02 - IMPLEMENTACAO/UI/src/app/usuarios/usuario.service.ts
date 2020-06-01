import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Usuario } from '../core/domain/Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  url = 'http://localhost:8080/usuarios';

  constructor(
    private httpClient: HttpClient
  ) { }

  async adicionar(usuario: Usuario): Promise<Usuario> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
      });
    return this.httpClient.post(this.url, JSON.stringify(usuario), {headers}).toPromise().then();
  }

}
