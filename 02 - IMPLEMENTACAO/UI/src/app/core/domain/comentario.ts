import { UsuarioCodigo } from './Usuario';
import { PostagemCodigo } from './postagem';

export class ComentarioInput {
  texto: string;
  usuario = new UsuarioCodigo();
  postagem = new PostagemCodigo();
}

export class ComentarioOutput {
  codigo: number;
  texto: string;
  autor: string;
}
