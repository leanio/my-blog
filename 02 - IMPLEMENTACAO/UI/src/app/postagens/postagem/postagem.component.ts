import { Component, OnInit } from '@angular/core';
import { PostagemService } from '../postagem.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PostagemOutput } from 'src/app/core/domain/postagem';

@Component({
  selector: 'app-postagem',
  templateUrl: './postagem.component.html',
  styleUrls: ['./postagem.component.css']
})
export class PostagemComponent implements OnInit {

  postagem: PostagemOutput;

  constructor(
    private postagemService: PostagemService,
    private errorHandlerService: ErrorHandlerService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.buscarPeloCodigo();
  }

  buscarPeloCodigo(): void {
    this.postagemService.buscarPeloCodigo(this.codigoPostagem()).then(postagem => {
      this.postagem = postagem;
    }).catch(erro => this.errorHandlerService.handle(erro));
  }

  codigoPostagem(): number {
    return this.activatedRoute.snapshot.params.codigo;
  }

}
