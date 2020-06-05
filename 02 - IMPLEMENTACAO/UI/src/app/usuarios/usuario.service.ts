import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { UsuarioComSenhaInput, UsuarioInput, UsuarioOutput } from '../core/domain/Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  url = 'http://localhost:8080/usuarios';

  constructor(
    private httpClient: HttpClient
  ) { }

  async adicionar(usuario: UsuarioComSenhaInput): Promise<UsuarioOutput> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
      });
    return this.httpClient.post(this.url, JSON.stringify(usuario), {headers}).toPromise().then();
  }

  async atualizar(codigo: number, usuario: UsuarioInput): Promise<UsuarioOutput> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
      });
    return this.httpClient.put(`${this.url}/${codigo}`, JSON.stringify(usuario), {headers}).toPromise().then();
  }

  async buscarPeloCodigo(codigo: number): Promise<UsuarioOutput> {
    return this.httpClient.get(`${this.url}/${codigo}`).toPromise().then();
  }

}
