import { Component, OnInit } from '@angular/core';
import { PostagemService } from '../postagem.service';
import { PostagemFilter } from 'src/app/core/filter/postagem-filter';
import { PostagemOutput } from 'src/app/core/domain/postagem';

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
    private postagemService: PostagemService
  ) { }

  ngOnInit(): void {
    this.filtrar();
  }

  filtrar() {
    console.log('aaaaaaaaaaaaaaaaaaaa');

    this.postagemService.filtrar(this.filtro).then(dados => {
      this.postagens = dados.postagens;
      this.totalPaginas = dados.totalPaginas;
    });
  }

}
