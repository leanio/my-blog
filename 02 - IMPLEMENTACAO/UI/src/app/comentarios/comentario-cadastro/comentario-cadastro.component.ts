import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ComentariosModule } from '../comentarios.module';
import { ComentarioInput } from 'src/app/core/domain/comentario';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { PostagemOutput } from 'src/app/core/domain/postagem';
import { ComentarioService } from '../comentario.service';
import { ToastService } from 'src/app/core/toast.service';
import { OauthService } from 'src/app/seguranca/oauth.service';

@Component({
  selector: 'app-comentario-cadastro',
  templateUrl: './comentario-cadastro.component.html',
  styleUrls: ['./comentario-cadastro.component.css']
})
export class ComentarioCadastroComponent implements OnInit {

  comentario = new ComentarioInput();

  @Input() codigoPostagem: number;

  @Output() comentarioAdicionado = new EventEmitter();

  constructor(
    private errorHandlerService: ErrorHandlerService,
    private comentarioService: ComentarioService,
    private oauthService: OauthService,
    private toastService: ToastService
  ) { }

  ngOnInit(): void {
    this.comentario.postagem.codigo = this.codigoPostagem;
    this.comentario.usuario.codigo = this.oauthService.codigoUsuario();
  }

  adicionarComentario(): void {
    this.comentarioService.adicionar(this.comentario).then(() => {
      this.toastService.toast('Comentario adicionado à postagem');
      this.comentarioAdicionado.emit(null);
    }).catch(erro => this.errorHandlerService.handle(erro));
  }

}
