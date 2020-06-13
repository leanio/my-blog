import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { PostagemInput, PostagemOutput } from '../core/domain/postagem';
import { PostagemFilter } from '../core/filter/postagem-filter';

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

  async filtrar(filter: PostagemFilter): Promise<{postagens: PostagemOutput[], totalPaginas: number}> {
    let params = new HttpParams();

    if (filter.titulo) {
      params = params.append('titulo', filter.titulo);
    }

    params = params.append('page', filter.pagina.toString());
    params = params.append('size', filter.itensPorPagina.toString());

    return this.httpCliente.get(this.url, {params}).toPromise<any>().then(response => {
      const dados = {
        postagens: response.content,
        totalPaginas: response.totalPages
      };

      return dados;
    });
  }
}
