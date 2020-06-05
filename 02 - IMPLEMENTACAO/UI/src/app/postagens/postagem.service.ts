import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { PostagemInput, PostagemOutput } from '../core/domain/postagem';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {

  url = 'http://localhost:8080/postagens';

  constructor(
    private httpCliente: HttpClient
  ) { }

  async adicionar(postagem: PostagemInput): Promise<PostagemInput> {
    return this.httpCliente.post(this.url, postagem).toPromise().then();
  }

  async atualizar(codigo: number, postagem: PostagemInput): Promise<PostagemInput> {
    return this.httpCliente.put(`${this.url}/${codigo}`, postagem).toPromise().then();
  }

  async buscarPeloCodigo(codigo: number): Promise<PostagemOutput> {
    return this.httpCliente.get(`${this.url}/${codigo}`).toPromise().then();
  }

}
