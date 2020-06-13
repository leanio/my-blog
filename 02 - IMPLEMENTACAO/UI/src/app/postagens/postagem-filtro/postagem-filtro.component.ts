import { Component, OnInit } from '@angular/core';
import { PostagemService } from '../postagem.service';
import { PostagemFilter } from 'src/app/core/filter/postagem-filter';
import { PostagemOutput } from 'src/app/core/domain/postagem';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';

@Component({
  selector: 'app-postagem-filtro',
  templateUrl: './postagem-filtro.component.html',
  styleUrls: ['./postagem-filtro.component.css']
})
export class PostagemFiltroComponent implements OnInit {

  postagens: PostagemOutput[] = [];
  totalPaginas = 0;

  filtro = new PostagemFilter();

  constructor(
    private postagemService: PostagemService,
    private errorHandlerService: ErrorHandlerService
  ) { }

  ngOnInit(): void {
    this.filtrar();
  }

  filtrar(): void {
    this.postagemService.filtrar(this.filtro).then(dados => {
      this.postagens = dados.postagens;
      this.totalPaginas = dados.totalPaginas;
    }).catch(erro => this.errorHandlerService.handle(erro));
  }

}
