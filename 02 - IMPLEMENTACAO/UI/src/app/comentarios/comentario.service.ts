import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ComentarioInput, ComentarioOutput } from '../core/domain/comentario';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {

  url = 'http://localhost:8080/comentarios';

  constructor(
    private httpCliente: HttpClient
  ) { }

  async adicionar(comentario: ComentarioInput): Promise<ComentarioOutput> {
    console.log(comentario);

    return this.httpCliente.post(this.url, comentario).toPromise().then();
  }

}
