import { Component, OnInit } from '@angular/core';
import { PostagemService } from '../postagem.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { ToastService } from 'src/app/core/toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PostagemInput } from 'src/app/core/domain/postagem';
import { OauthService } from 'src/app/seguranca/oauth.service';

declare var $: any;

@Component({
  selector: 'app-postagem-cadastro',
  templateUrl: './postagem-cadastro.component.html',
  styleUrls: ['./postagem-cadastro.component.css']
})
export class PostagemCadastroComponent implements OnInit {

  postagem = new PostagemInput();

  constructor(
    private postagemService: PostagemService,
    private oauthService: OauthService,
    private errorHandlerService: ErrorHandlerService,
    private toastServie: ToastService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    $('textarea#corpo').characterCounter();

    if (this.codigoPostagem()) {
      this.carregarPostagem();
    }

    this.postagem.usuario.codigo = this.oauthService.codigoUsuario();
  }

  codigoPostagem(): number {
    return this.activatedRoute.snapshot.params.codigo;
  }

  salvarPostagem(): void {
    if (this.isEditando()) {
      this.atualizarPostagem();
    } else {
      this.adicionarPostagem();
    }
  }

  adicionarPostagem(): void {
    this.postagemService.adicionar(this.postagem).then(() => {
      this.toastServie.toast('Postagem adicionada com sucesso');
      this.router.navigateByUrl('/');
    }).catch(erro => this.errorHandlerService.handle(erro));
  }

  atualizarPostagem(): void {
    this.postagemService.atualizar(this.codigoPostagem(), this.postagem).then(() => {
      this.toastServie.toast('Postagem atualizada com sucesso');
      this.router.navigateByUrl('/');
    }).catch(erro => this.errorHandlerService.handle(erro));
  }

  carregarPostagem(): void {
    this.postagemService.buscarPeloCodigo(this.codigoPostagem()).then(postagem => {
      this.postagem = {titulo: postagem.titulo, corpo: postagem.corpo, usuario: {codigo: this.oauthService.codigoUsuario()}};
      $('#tituloLabel').addClass('active');
      $('#corpoLabel').addClass('active');
    }).catch(erro => this.errorHandlerService.handle(erro));
  }

  isEditando(): boolean {
    return this.codigoPostagem() !== undefined;
  }

}
