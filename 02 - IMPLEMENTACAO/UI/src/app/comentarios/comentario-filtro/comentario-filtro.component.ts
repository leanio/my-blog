import { Component, OnInit, Input } from '@angular/core';
import { ComentarioService } from '../comentario.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { ComentarioFilter } from 'src/app/core/filter/comentario-filter';
import { ComentarioOutput } from 'src/app/core/domain/comentario';

@Component({
  selector: 'app-comentario-filtro',
  templateUrl: './comentario-filtro.component.html',
  styleUrls: ['./comentario-filtro.component.css']
})
export class ComentarioFiltroComponent implements OnInit {

  comentarios: ComentarioOutput[];
  totalPaginas = 0;

  @Input() codigoPostagem: number;

  filter = new ComentarioFilter();

  constructor(
    private errorHandlerService: ErrorHandlerService,
    private comentarioService: ComentarioService
  ) { }

  ngOnInit(): void {
    this.filter.codigoPostagem = this.codigoPostagem;

    this.filtrar();
  }

  remover(codigo: number): void {
    console.log(codigo);

    this.comentarioService.remover(codigo).then(() => {

    }).catch(erro => this.errorHandlerService.handle(erro));
  }

  filtrar(): void {
    this.comentarioService.filtrar(this.filter).then(dados => {
      this.comentarios = dados.comentarios;
      this.totalPaginas = dados.totalPaginas;
    }).catch(erro => this.errorHandlerService.handle(erro));
  }

}
