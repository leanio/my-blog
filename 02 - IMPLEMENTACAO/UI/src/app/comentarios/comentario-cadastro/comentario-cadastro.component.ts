import { Component, OnInit, Input } from '@angular/core';
import { ComentariosModule } from '../comentarios.module';
import { ComentarioInput } from 'src/app/core/domain/comentario';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { PostagemOutput } from 'src/app/core/domain/postagem';
import { ComentarioService } from '../comentario.service';
import { ToastService } from 'src/app/core/toast.service';

@Component({
  selector: 'app-comentario-cadastro',
  templateUrl: './comentario-cadastro.component.html',
  styleUrls: ['./comentario-cadastro.component.css']
})
export class ComentarioCadastroComponent implements OnInit {

  comentario = new ComentarioInput();

  @Input() codigoPostagem: number;

  constructor(
    private errorHandlerService: ErrorHandlerService,
    private comentarioService: ComentarioService,
    private toastService: ToastService
  ) { }

  ngOnInit(): void {
    this.comentario.postagem.codigo = this.codigoPostagem;
    this.comentario.usuario.codigo = this.codigoUsuarioFake();
  }

  adicionarComentario(): void {
    this.comentarioService.adicionar(this.comentario).then(() => {
      this.toastService.toast('Comentario adicionado à postagem');
    }).catch(erro => this.errorHandlerService.handle(erro));
  }

  codigoUsuarioFake(): number {
    return 1;
  }

}
