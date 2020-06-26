import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ComentarioInput, ComentarioOutput } from '../core/domain/comentario';
import { ComentarioFilter } from '../core/filter/comentario-filter';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {

  url = 'http://localhost:8080/comentarios';

  constructor(
    private httpCliente: HttpClient
  ) { }

  async adicionar(comentario: ComentarioInput): Promise<ComentarioOutput> {
    return this.httpCliente.post(this.url, comentario).toPromise().then();
  }

  async remover(codigo: number): Promise<void> {
    return this.httpCliente.delete(`${this.url}/${codigo}`).toPromise().then(() => null);
  }

  async filtrar(filter: ComentarioFilter): Promise<{comentarios: ComentarioOutput[], totalPaginas: number}> {
    let params = new HttpParams();

    if (filter.codigoPostagem) {
      params = params.append('codigoPostagem', filter.codigoPostagem.toString());
    }

    params = params.append('page', filter.pagina.toString());
    params = params.append('size', filter.itensPorPagina.toString());

    return this.httpCliente.get(this.url, {params}).toPromise<any>().then(response => {
      const dados = {
        comentarios: response.content,
        totalPaginas: response.totalPages
      };

      return dados;
    });
  }

}
